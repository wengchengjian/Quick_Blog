/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysRole;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysRoleService:SysRoleService.java v1.0 2021/12/7 2:46 下午 wengchengjian Exp $
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色 指定当前创建者
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<SysRole> queryPage(Integer pageNum,Integer pageSize,Integer createUserId);

    /**
     * 管理员分页查询全部角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<SysRole> queryPage(Integer pageNum,Integer pageSize);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatch(Integer[] ids);

    /**
     * 根据创建人查询role
     * @param createUserId
     * @return
     */
    List<Integer> queryRoleIdList(Integer createUserId);

    /**
     * 通过roleId获取Role
     * @param roleId
     * @return
     */
    SysRole findByRoleId(Integer roleId);

    /**
     * 通过roleName查询roleId
     * @param authority
     * @return
     */
    Integer findByRoleName(String authority);

    /**
     * 通过ids批量查询
     * @param ids
     * @return
     */
    List<SysRole> selectListById(List<Integer> ids);

    /**
     * 插入一个角色
     * @param role
     */
    void insert(SysRole role);

    /**
     * 角色是否存在
     * @param roleName
     * @param userId
     * @return
     */
    boolean isExist(String roleName, Integer userId);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(SysRole role);
}
