/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.request.QueryLinkListRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Link;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version linkService:LinkService.java v1.0 2021/12/1 7:22 下午 wengchengjian Exp $
 */
public interface LinkService extends IService<Link> {
    /**
     * 分页查询
     * @pageNum 页码
     * @pageSize 每页大小
     * @title 链接名称
     * @return
     */
    PageQuery<Link> queryPage(QueryLinkListRequest request);
}
