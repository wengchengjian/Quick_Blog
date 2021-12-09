/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.mapper.sys.SysUserMapper;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    /**
     * 分页查询用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public PageQuery<SysUser> queryPage(Integer pageNum, Integer pageSize,String userName,Integer createUserId) {
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        IPage<SysUser> res = baseMapper.selectPage(page,new QueryWrapper<SysUser>().lambda()
                .like(StringUtils.isNotBlank(userName),SysUser::getUsername,userName)
                .eq(createUserId!=null,SysUser::getCreateUserId,createUserId));
        return new PageQuery<>(res);
    }

    /**
     * 更新密码
     * @param userId 用户唯一id
     * @param oldPassword   老密码
     * @param newPassword   新密码
     * @return
     */
    @Override
    public Boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        SysUser sysUser = new SysUser();
        sysUser.setPassword(newPassword);
        return this.update(sysUser,new UpdateWrapper<SysUser>().lambda()
                .eq(userId!=null,SysUser::getUserId,userId)
                .eq(SysUser::getPassword,oldPassword));
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

    private void checkRole(SysUser user){

    }
}
