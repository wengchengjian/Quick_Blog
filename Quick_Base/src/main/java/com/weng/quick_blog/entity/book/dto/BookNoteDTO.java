/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.book.dto;

import com.weng.quick_blog.entity.book.BookNote;
import com.weng.quick_blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version BookNoteDTO:BookNoteDTO.java v1.0 2021/12/5 6:32 下午 wengchengjian Exp $
 */
@Data
public class BookNoteDTO extends BookNote {
    private List<Tag> tagList;
}
