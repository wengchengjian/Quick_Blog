/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.book.vo;

import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.book.BookNote;
import com.weng.quick_blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version :BookNoteVO.java v1.0 2021/12/3 11:04 上午 wengchengjian Exp $
 */
@Data
public class BookNoteVO extends BookNote {
    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属书本
     */
    private Book book;
}
