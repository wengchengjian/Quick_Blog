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
     * 分页查询角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<SysRole> queryPage(Integer pageNum,Integer pageSize,String roleName,Integer createUserId);

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

    SysRole findByRoleId(Integer roleId);

    Integer findByRoleName(String authority);
}
