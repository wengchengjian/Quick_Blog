/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.book.vo.BookVO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookMapper:BookMapper.java v1.0 2021/12/3 11:07 上午 wengchengjian Exp $
 */
public interface BookMapper extends BaseMapper<Book> {
    /**
     * 获取book列表
     * @param page
     * @param title
     * @return
     */
    List<Book> listBook(Page<Book> page,String title,Boolean recommend,Boolean favorite,Boolean grade);

    /**
     * 根据条件分页查询
     * @param page
     * @param recommend
     * @param categoryId
     * @param latest
     * @param favorite
     * @return
     */
    List<BookVO> queryPageCondition(Page<BookVO> page, Boolean recommend,
                                    String categoryId, Boolean latest, Boolean favorite,String title);

    /**
     * 增加阅读记录
     * @param bookId
     */
    void addReadNum(Integer bookId,Integer num);

    /**
     * 增加点赞记录
     * @param bookId
     * @param num
     */
    void addLikeNum(Integer bookId,Integer num);

    /**
     * 判断某个类别下是否有图书
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);

    /**
     * 根据id查询包含tag,bookNode的实体
     * @param id
     * @return
     */
    BookVO selectByIdWithSubList(Integer id);

}
