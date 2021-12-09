/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weng.quick_blog.entity.book.BookNote;
import com.weng.quick_blog.entity.book.vo.BookNoteVO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version BookNoteMapper:BookNoteMapper.java v1.0 2021/12/3 11:07 上午 wengchengjian Exp $
 */
public interface BookNoteMapper extends BaseMapper<BookNote> {

    List<BookNoteVO> queryPageCondition(Page<BookNoteVO> page, Boolean recommend, String categoryId, Boolean latest, Boolean favorite,String title);

    void addReadNum(Integer bookNoteId,Integer num);

    void addLikeNum(Integer bookNoteId,Integer num);

    /**
     * 获取简单对象
     * @param bookNoteId
     * @return
     */
    BookNote getSimpleBookNote(Integer bookNoteId);

    /**
     * 获取简单list
     * @param bookId
     * @return
     */
    List<BookNote> listSimpleBookNoteByBookId(Integer bookId);

    /**
     * 判断该类别下是否有笔记
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);
}
