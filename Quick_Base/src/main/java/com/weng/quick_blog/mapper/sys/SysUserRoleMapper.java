/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.sys.SysUserRole;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserRoleMapper:SysUserRoleMapper.java v1.0 2021/12/7 2:16 下午 wengchengjian Exp $
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据userId查询roleId
     * @param userId
     * @return
     */
    List<Integer> queryRoleIdList(Integer userId);
}
