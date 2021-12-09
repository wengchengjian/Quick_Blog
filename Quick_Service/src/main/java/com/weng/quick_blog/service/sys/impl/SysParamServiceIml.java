/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysParam;
import com.weng.quick_blog.mapper.sys.SysParamMapper;
import com.weng.quick_blog.service.sys.SysParamService;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysParamServiceImpl:SysParamServiceIml.java v1.0 2021/12/7 3:09 下午 wengchengjian Exp $
 */
public class SysParamServiceIml extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {
    /**
     * 分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public PageQuery<SysParam> queryPage(Integer pageNum, Integer pageSize) {
        return null;
    }
}
