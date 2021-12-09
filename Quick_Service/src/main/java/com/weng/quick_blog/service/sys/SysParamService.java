/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysParam;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysParamService:SysParamService.java v1.0 2021/12/7 3:07 下午 wengchengjian Exp $
 */
public interface SysParamService extends IService<SysParam> {

    /**
     * 分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return
     */
    PageQuery<SysParam> queryPage(Integer pageNum,Integer pageSize);
}
