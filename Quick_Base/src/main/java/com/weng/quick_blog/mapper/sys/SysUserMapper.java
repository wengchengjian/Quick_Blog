/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.sys.SysUser;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserMapper:SysUserMapper.java v1.0 2021/12/7 2:15 下午 wengchengjian Exp $
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询用户所有的权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Integer userId);

    /**
     * 查询用户的menuId
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);
}
