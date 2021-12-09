/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysUser;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserService:SysUserService.java v1.0 2021/12/7 2:50 下午 wengchengjian Exp $
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<SysUser> queryPage(Integer pageNum,Integer pageSize,String userName,Integer createUserId);

    /**
     * 更新密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer userId,String oldPassword,String newPassword);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(Integer[] ids);

    SysUser findByName(String username);
}
