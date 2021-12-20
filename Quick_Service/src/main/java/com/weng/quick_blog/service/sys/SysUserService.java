/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.entity.sys.vo.SysUserVO;

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
    PageQuery<SysUserVO> queryPage(Integer pageNum,Integer pageSize,String userName,Integer createUserId);

    /**
     * 管理员 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<SysUserVO> queryPage(Integer pageNum, Integer pageSize, String userName);

    /**
     * 更新密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(String oldPassword,String newPassword);



    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(Integer[] ids);

    SysUser findByName(String username);

    /**
     * 获取当前安全用户，没有私密信息
     * @return
     */
    SysUserVO getCurrentUser();

    /**
     * 获取当前用户，包含私密信息
     * @return
     */
    SafeUserDetails getCurrentSysUser();

    SysUserVO infoById(Integer id);

    /**
     * 当前用户是否是管理员
     * @return
     */
    boolean isAdmin();
}
