/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.enums.ModuleEnum;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.book.dto.BookDTO;
import com.weng.quick_blog.entity.book.vo.BookVO;
import com.weng.quick_blog.mapper.book.BookMapper;
import com.weng.quick_blog.service.book.BookService;
import com.weng.quick_blog.service.operation.CategoryService;
import com.weng.quick_blog.service.operation.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookServiceImpl:BookServiceImpl.java v1.0 2021/12/5 5:53 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public PageQuery<BookVO> queryPage(Integer pageNum, Integer pageSize, Boolean recommend, String categoryId,
                                       Boolean latest, Boolean favorite,String title) {
        Page<BookVO> page = new Page<>(pageNum,pageSize);

        List<BookVO> bookList = baseMapper.queryPageCondition(page,recommend,categoryId,latest,favorite,title);
        bookList.forEach((bookvo)->{
            // 查询分类
            String categoryList = categoryService.queryCategoryArr(bookvo.getCategoryId());

            bookvo.setCategoryListStr(categoryList);

        });
        page.setRecords(bookList);
        return new PageQuery<>(page);
    }

    @Override
    public void saveBook(BookDTO book) {
        baseMapper.insert(book);
        tagService.saveTagOrInsert(book.getTagList(),book.getId(), ModuleEnum.BOOK.getValue());
    }

    @Override
    public BookDTO getBook(Integer id) {
        Book readbook = baseMapper.selectById(id);
        BookDTO readBookDTO = null;
        if(readbook!=null){
            readBookDTO  = new BookDTO();
            BeanUtils.copyProperties(readbook,readBookDTO);
            readBookDTO.setTagList(tagService.listByLinkId(readBookDTO.getId(),ModuleEnum.BOOK.getValue()));
            return readBookDTO;
        }
        return null;
    }

    @Override
    public void updateBook(BookDTO book) {
        // 删除多对多所属标签
        tagService.deleteTagLink(book.getId(),ModuleEnum.BOOK.getValue());
        // 更新所属标签
        tagService.saveTagOrInsert(book.getTagList(),book.getId(), ModuleEnum.BOOK.getValue());
        // 更新图书
        baseMapper.updateById(book);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        // 先删除书与标签多对多的关联关系
        Arrays.stream(ids).forEach(bookId->{
            tagService.deleteTagLink(bookId,ModuleEnum.BOOK.getValue());
        });
        // 再删除书
        baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }
}
