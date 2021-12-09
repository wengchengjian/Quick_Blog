/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.sys.SysUserRole;
import com.weng.quick_blog.mapper.sys.SysUserRoleMapper;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserRoleServiceImpl:SysUserRoleServiceImpl.java v1.0 2021/12/7 2:59 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    /**
     *
     * @param roleIds 角色id数组
     */
    @Override
    public void deleteBatchByRoleId(Integer[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId->{
            baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
                    .eq(roleId!=null,SysUserRole::getRoleId,roleId));
        });
    }

    /**
     *
     * @param userIds 用户id数组
     */
    @Override
    public void deleteBatchByUserId(Integer[] userIds) {
        Arrays.stream(userIds).forEach(userId->{
            baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
                    .eq(userId!=null,SysUserRole::getUserId,userId));
        });
    }

    /**
     * 更新或保存用户角色
     * @param userId 用户id
     * @param roleIdList 角色列表
     */
    @Override
    public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
        // 先删除用户与角色的关系
        baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
                .eq(userId!=null,SysUserRole::getUserId,userId));

        Optional.ofNullable(roleIdList).ifPresent(roleIds->{
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            roleIds.stream().forEach(roleId->{
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                list.add(sysUserRole);
            });
            this.saveBatch(list);
        });
    }

    /**
     * 根据userId 查询roleId
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        return baseMapper.queryRoleIdList(userId);
    }
}
