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
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.entity.sys.vo.SysUserVO;
import com.weng.quick_blog.mapper.sys.SysUserMapper;
import com.weng.quick_blog.service.sys.SysRoleService;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserService:SysUserServiceImpl.java v1.0 2021/12/7 2:53 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysRoleService sysRoleService;




    /**
     * 分页查询用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public PageQuery<SysUserVO> queryPage(Integer pageNum, Integer pageSize,String userName,Integer createUserId) {
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        IPage<SysUser> res = baseMapper.selectPage(page,new QueryWrapper<SysUser>().lambda()
                .like(StringUtils.isNotBlank(userName),SysUser::getUsername,userName)
                .eq(createUserId!=null,SysUser::getCreateUserId,createUserId));

        PageQuery pageQuery = new PageQuery(res);

        List<SysUserVO> newList = new ArrayList<>();
        List list = pageQuery.getList();
        for (Object o : list){
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(o,sysUserVO);
            newList.add(sysUserVO);
        }
        pageQuery.setList(newList);

        return pageQuery;
    }

    /**
     * 管理员 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @param userName
     * @return
     */
    @Override
    public PageQuery<SysUserVO> queryPage(Integer pageNum, Integer pageSize, String userName) {
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        IPage<SysUser> res = baseMapper.selectPage(page,new QueryWrapper<SysUser>().lambda()
                .like(StringUtils.isNotBlank(userName),SysUser::getUsername,userName).orderByAsc(SysUser::getCreateTime));

        PageQuery pageQuery = new PageQuery(res);

        List<SysUserVO> newList = new ArrayList<>();
        List list = pageQuery.getList();
        for (Object o : list){
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(o,sysUserVO);
            newList.add(sysUserVO);
        }
        pageQuery.setList(newList);

        return pageQuery;
    }

    /**
     * 更新密码
     * @param oldPassword   老密码
     * @param newPassword   新密码
     * @return
     */
    @Override
    public Boolean updatePassword(String oldPassword, String newPassword) {
        SafeUserDetails currentUser = this.getCurrentSysUser();

        if(!bCryptPasswordEncoder.matches(oldPassword,currentUser.getPassword())){
            return false;
        }
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        SysUser byId = this.getById(currentUser.getUserId());
        byId.setPassword(encodePassword);

        this.updateById(byId);
        //TODO 更新密码后需要强制用户重新登录,可以将token放入redis中，然后将对应的token过期即可

        return true;
    }

    /**
     * 批量删除
     * @param ids 需要删除的用户id数组
     */
    @Override
    public void deleteBatch(Integer[] ids) {
        // 先删除用户与角色的关联
        sysUserRoleService.deleteBatchByUserId(ids);

        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public SysUser findByName(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .eq(StringUtils.isNotBlank(username),SysUser::getUsername,username));
    }

    @Override
    public SysUserVO getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        SafeUserDetails user = (SafeUserDetails) context.getAuthentication().getPrincipal();

        return this.infoById(user.getUserId());
    }

    @Override
    public SafeUserDetails getCurrentSysUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        SafeUserDetails user = (SafeUserDetails) context.getAuthentication().getPrincipal();

        return user;
    }

    @Override
    public SysUserVO infoById(Integer id) {
        SysUser byId = baseMapper.selectById(id);

        List<Integer> roleIds = sysUserRoleService.queryRoleIdList(id);

        List<SysRole> sysRoles = sysRoleService.selectListById(roleIds);

        byId.setRoleList(sysRoles);

        SysUserVO res = new SysUserVO();
        BeanUtils.copyProperties(byId,res);

        return res;
    }

    /**
     * 当前用户是否是管理员
     * @return
     */
    @Override
    public boolean isAdmin() {
        SafeUserDetails currentUser = this.getCurrentSysUser();
        return currentUser.getAuthorities().contains(SysConstants.ADMIN);
    }

    private void checkRole(SysUser user){

    }
}
