/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.constants.SysConstants;
import com.weng.quick_blog.common.exception.GlobalException;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.mapper.sys.SysRoleMapper;
import com.weng.quick_blog.service.sys.SysRoleService;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public PageQuery<SysRole> queryPage(Integer pageNum, Integer pageSize,String roleName,Integer createUserId) {
        Page<SysRole> page = new Page<>(pageNum,pageSize);
        IPage<SysRole> res=baseMapper.selectPage(page, new QueryWrapper<SysRole>().lambda()
                .like(StringUtils.isNotBlank(roleName),SysRole::getRoleName,roleName)
                .eq(createUserId!=null,SysRole::getCreateUserId,createUserId)
        );
        return new PageQuery<SysRole>(res);
    }

    /**
     * 批量删除角色
     * @param ids
     */
    @Override
    public void deleteBatch(Integer[] ids) {
        //TODO 删除关联

        // 删除角色与用户关联

        sysUserRoleService.deleteBatchByRoleId(ids);


        // 删除角色
        this.removeByIds(Arrays.asList(ids));

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
        return baseMapper.selectById(roleId);
    }

    @Override
    public Integer findByRoleName(String authority) {
        return baseMapper.selectOne(new QueryWrapper<SysRole>().lambda()
                .eq(StringUtils.isNotBlank(authority),SysRole::getRoleName,authority)).getRoleId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysRole role) {
        role.setCreateTime(new Date());

        //检查越权
        checkPerms(role);

        baseMapper.insert(role);



        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SysRole role) {

        //检查越权
        checkPerms(role);

        baseMapper.updateById(role);



        return true;
    }

    // TODO 待修改逻辑
    private void checkPerms(SysRole role){
        if(role.getCreateUserId().equals(SysConstants.ADMIN)){
            return ;
        }

        // 查询创建者的权限列表
        List<Integer> menuIdList = baseMapper.queryRoleIdList(role.getCreateUserId());

        if(!menuIdList.containsAll(role.getMenuIdList())){
            log.error("角色新增的权限已超出创建者的权限,创建者Id: {},角色Id: {}",role.getCreateUserId(),role.getRoleId());
            throw new GlobalException(String.format("新增角色的权限已超出创建者的权限,创建者Id: %s,角色Id: %s",role.getCreateUserId(),role.getRoleId()));
        }
    }
}
