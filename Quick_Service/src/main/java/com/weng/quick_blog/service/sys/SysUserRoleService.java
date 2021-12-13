/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.entity.sys.SysUserRole;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserRoleService:SysUserRoleService.java v1.0 2021/12/7 2:56 下午 wengchengjian Exp $
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    /**
     *
     * @param roleIds 角色id数组
     */
    void deleteBatchByRoleId(Integer[] roleIds);

    /**
     *
     * @param userIds 用户id数组
     */
    void deleteBatchByUserId(Integer[] userIds);

    /**
     * 更新或保存用户角色
     * @param userId 用户id
     * @param roleIdList 角色列表
     */
    void saveOrUpdate(Integer userId, List<Integer> roleIdList);

    /**
     * 根据userId 查询roleId
     * @param userId 用户id
     * @return
     */
    List<Integer> queryRoleIdList(Integer userId);

    /**
     * 根据roleId 查询userId
     * @param roleId 用户id
     * @return
     */
    List<Integer> queryUserIdList(Integer roleId);
}
