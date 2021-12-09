/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.book.dto;

import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookDTO:BookDTO.java v1.0 2021/12/5 5:51 下午 wengchengjian Exp $
 */
@Data
public class BookDTO extends Book {
    List<Tag> tagList;

}
