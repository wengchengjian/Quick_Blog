/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.exception.GlobalException;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.entity.sys.SysRolePerm;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.mapper.sys.SysRoleMapper;
import com.weng.quick_blog.service.sys.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysRoleServiceImpl:SysRoleServiceImpl.java v1.0 2021/12/7 2:48 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {



    @Autowired
    private SysRolePermService sysRolePermService;

    @Autowired
    private SysPermService sysPermService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageQuery<SysRole> queryPage(Integer pageNum, Integer pageSize,Integer createUserId) {
        Page<SysRole> page = new Page<>(pageNum,pageSize);
        IPage<SysRole> res=baseMapper.selectPage(page, new QueryWrapper<SysRole>().lambda()
                .eq(createUserId!=null,SysRole::getCreateUserId,createUserId)
        );
        res.getRecords().forEach(sysRole->{
            Integer createUserId1 = sysRole.getCreateUserId();
            SysUser byId = sysUserService.getById(createUserId1);
            sysRole.setRoleName(byId.getAppellation());
        });
        return new PageQuery<SysRole>(res);
    }

    @Override
    public PageQuery<SysRole> queryPage(Integer pageNum, Integer pageSize) {
        Page<SysRole> page = new Page<>(pageNum,pageSize);
        IPage<SysRole> res=baseMapper.selectPage(page, new QueryWrapper<SysRole>());

        res.getRecords().forEach(sysRole->{
            Integer createUserId1 = sysRole.getCreateUserId();
            SysUser byId = sysUserService.getById(createUserId1);
            sysRole.setRoleName(byId.getAppellation());
        });
        return new PageQuery<SysRole>(res);
    }

    /**
     * 批量删除角色 用户与角色强相关，不能在用户具有角色的情况下直接删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] ids) {
        List<Integer> collect = Arrays.stream(ids).filter(id -> {
            SysRole byRoleId = this.findByRoleId(id);

            // 查询是否有用户绑定这个角色
            List<Integer> integers = sysUserRoleService.queryUserIdList(id);
            if (integers != null && !integers.isEmpty()) {
                throw new GlobalException(String.format("%s角色关联了其他用户,无法删除",byRoleId.getRoleName()));
            }
            // 其他情况则删除
            return true;
        }).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            // 先删除权限与角色的关联
            sysRolePermService.delteBatchByRoleId(collect);

            // 再删除角色
            baseMapper.deleteBatchIds(collect);
        }



    }

    /**
     * 根据创建人查询role
     * @param createUserId
     * @return
     */
    @Override
    public List<Integer> queryRoleIdList(Integer createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    @Override
    public SysRole findByRoleId(Integer roleId) {
        SysRole sysRole = baseMapper.selectById(roleId);

        Integer createUserId1 = sysRole.getCreateUserId();
        SysUser byId = sysUserService.getById(createUserId1);
        sysRole.setCreateUserName(byId.getAppellation());

        return sysRole;
    }

    @Override
    public Integer findByRoleName(String authority) {
        return baseMapper.selectOne(new QueryWrapper<SysRole>().lambda()
                .eq(StringUtils.isNotBlank(authority),SysRole::getRoleName,authority)).getRoleId();
    }

    @Override
    public List<SysRole> selectListById(List<Integer> ids) {
        List<SysRole> sysRoles = baseMapper.selectBatchIds(ids);
        return sysRoles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysRole role) {

        SafeUserDetails currentUser = sysUserService.getCurrentUser();

        role.setCreateUserId(currentUser.getUserId());
        role.setCreateTime(new Date());

        //插入角色
        baseMapper.insert(role);
        //更新角色与权限的关联
        List<String> permList = Optional.ofNullable(role.getPermList()).orElse(new ArrayList<>());

        List<SysRolePerm> collect = permList.stream().map(perm -> {
            SysPerm byName = sysPermService.findByName(perm);
            return new SysRolePerm(role.getRoleId(),byName.getId());
        }).collect(Collectors.toList());

        // 重复的权限直接覆盖
        if(!collect.isEmpty()){
            sysRolePermService.saveOrUpdateBatch(collect);
        }
    }

    @Override
    public boolean isExist(String roleName, Integer userId) {
        return baseMapper.selectOne(new QueryWrapper<SysRole>().lambda()
                .eq(StringUtils.isNotEmpty(roleName),SysRole::getRoleName,roleName)
                .eq(userId!=null,SysRole::getCreateUserId,userId))!=null;
    }

    /**
     * 更新角色
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRole role) {
        // TODO 参数应重新定一个class接受，项目最后优化时更改
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(role.getRoleId());
        sysRole.setRoleName(role.getRoleName());
        sysRole.setRemark(role.getRemark());
        //更新角色
        baseMapper.updateById(sysRole);


        //先删除之前的关系
        sysRolePermService.delteByRoleId(role.getRoleId());

        //更新权限与角色的关系
        List<String> permList = role.getPermList();

        // TODO 需要判断传递的权限是否合法，现在的方法是使用filter先过滤不合法的，但这样多查询了一次 显而易见的优化方法 可以将权限放进内存中，不再查数据库，数据库更新时同时同步到内存中
        List<SysRolePerm> collect = permList.stream()
                .filter(perm -> sysPermService.findByName(perm)!=null)
                .map(perm -> {
                    SysPerm byName = sysPermService.findByName(perm);
                    return new SysRolePerm(role.getRoleId(),byName.getId());
                })
                .collect(Collectors.toList());

        sysRolePermService.saveBatch(collect);
    }

}
