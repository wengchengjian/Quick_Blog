/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.entity.sys.SysPerm;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysPermService:SysPermService.java v1.0 2021/12/9 10:19 上午 wengchengjian Exp $
 */
public interface SysPermService extends IService<SysPerm> {
    /**
     * 查询当前权限路径
     * @param permId
     * @return
     */
    List<SysPerm> queryAllPerms(Integer permId);
}
