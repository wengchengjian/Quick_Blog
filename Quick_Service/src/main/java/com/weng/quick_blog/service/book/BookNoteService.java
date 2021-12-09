/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.BookNote;
import com.weng.quick_blog.entity.book.dto.BookNoteDTO;
import com.weng.quick_blog.entity.book.vo.BookNoteVO;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookNoteService:BookNoteService.java v1.0 2021/12/5 5:52 下午 wengchengjian Exp $
 */
public interface BookNoteService extends IService<BookNote> {
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param recommend
     * @param categoryId
     * @param latest
     * @param favorite
     * @return
     */
    PageQuery<BookNoteVO> queryPage(Integer pageNum,Integer pageSize,Boolean recommend,
                                    String categoryId,Boolean latest,Boolean favorite,String title);

    void saveBookNote(BookNoteDTO bookNote);


    void deleteBatch(Integer[] bookNoteIds);

    void updateBookNote(BookNoteDTO bookNote);

    BookNoteDTO getBookNote(Integer bookNoteId);

    boolean checkByCategory(Integer categoryId);
}
