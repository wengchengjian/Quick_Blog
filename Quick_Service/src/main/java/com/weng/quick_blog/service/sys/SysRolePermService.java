/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.entity.sys.SysRolePerm;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysRolePermService:SysRolePermService.java v1.0 2021/12/9 10:42 上午 wengchengjian Exp $
 */
public interface SysRolePermService extends IService<SysRolePerm> {
    /**
     * 查询角色拥有的权限id列表
     * @param roleId
     * @return
     */
    List<Integer> queryAllPermsIdsByRoleId(Integer roleId);
}
