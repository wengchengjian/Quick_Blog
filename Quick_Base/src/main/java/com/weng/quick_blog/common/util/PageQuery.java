/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version pageQuery:PageQuery.java v1.0 2021/12/1 7:33 下午 wengchengjian Exp $
 */
@Data
@NoArgsConstructor
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID =1L;

    private long totalCount;

    private long pageSize;

    private long totalPage;

    private long currentPage;

    private List<T> list;

    public PageQuery(List<T> list,int totalCount,int pageSize,int currentPage){
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int)Math.ceil((double)totalCount / pageSize);
    }

    public PageQuery(IPage<T> page){
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.currentPage = page.getCurrent();
        this.totalPage = page.getPages();
    }
}
