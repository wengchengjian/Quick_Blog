/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.book.BookSense;
import com.weng.quick_blog.mapper.book.BookSenseMapper;
import com.weng.quick_blog.service.book.BookSenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version BookSenseServiceImpl:BookSenseServiceImpl.java v1.0 2021/12/5 5:55 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class BookSenseServiceImpl extends ServiceImpl<BookSenseMapper, BookSense> implements BookSenseService {
    @Override
    public BookSense getBookSense(Integer bookId) {
        BookSense readSense = baseMapper.selectOne(new QueryWrapper<BookSense>()
                .lambda().eq(bookId!=null,BookSense::getBookId,bookId));
        return Optional.ofNullable(readSense).orElse(null);
    }
}
