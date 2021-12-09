/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.entity.book.BookSense;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version BookSenseService:BookSenseService.java v1.0 2021/12/5 5:52 下午 wengchengjian Exp $
 */
public interface BookSenseService extends IService<BookSense> {
    /**
     * 获取读后感
     * @param bookId
     * @return
     */
    BookSense getBookSense(Integer bookId);
}
