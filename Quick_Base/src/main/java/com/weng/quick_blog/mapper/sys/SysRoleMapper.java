/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.sys.SysRole;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version sysRoleMapper:SysRoleMapper.java v1.0 2021/12/7 2:14 下午 wengchengjian Exp $
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<Integer> queryRoleIdList(Integer createUserId);
}
