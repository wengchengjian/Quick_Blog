/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.book.dto.BookDTO;
import com.weng.quick_blog.entity.book.vo.BookVO;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookService:BookService.java v1.0 2021/12/5 5:51 下午 wengchengjian Exp $
 */
public interface BookService extends IService<Book> {
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
    PageQuery<BookVO> queryPage(Integer pageNum,Integer pageSize,Boolean recommend,
                                String categoryId, Boolean latest, Boolean favorite,String title);

    void saveBook(BookDTO book);

    BookDTO getBook(Integer id);

    void updateBook(BookDTO book);

    void deleteBatch(Integer[] ids);

    boolean checkByCategory(Integer categoryId);
}
