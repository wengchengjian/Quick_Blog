/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.book.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.enums.ModuleEnum;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.BookNote;
import com.weng.quick_blog.entity.book.dto.BookNoteDTO;
import com.weng.quick_blog.entity.book.vo.BookNoteVO;
import com.weng.quick_blog.mapper.book.BookNoteMapper;
import com.weng.quick_blog.service.book.BookNoteService;
import com.weng.quick_blog.service.book.BookService;
import com.weng.quick_blog.service.operation.CategoryService;
import com.weng.quick_blog.service.operation.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookNoteServiceImpl:BookNoteServiceImpl.java v1.0 2021/12/5 5:54 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {
    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Override
    public PageQuery<BookNoteVO> queryPage(Integer pageNum, Integer pageSize, Boolean recommend, String categoryId,
                                           Boolean latest, Boolean favorite,String title) {
        Page<BookNoteVO> page = new Page<>(pageNum,pageSize);
        List<BookNoteVO> bookNoteList = baseMapper.queryPageCondition(page,recommend,categoryId,latest,favorite,title);

        Optional.ofNullable(bookNoteList).ifPresent((bookNoteVOS)->{
            bookNoteVOS.forEach(bookNoteVO -> {
                // 查询分类
                String categoryList = categoryService.queryCategoryArr(bookNoteVO.getCategoryId());

                bookNoteVO.setCategoryListStr(categoryList);
            });
        });
        page.setRecords(bookNoteList);
        return new PageQuery<>(page);
    }

    @Override
    public void saveBookNote(BookNoteDTO bookNote) {
        baseMapper.insert(bookNote);
        tagService.saveTagOrInsert(bookNote.getTagList(),bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
    }

    @Override
    public void deleteBatch(Integer[] bookNoteIds) {
        Arrays.stream(bookNoteIds).forEach(bookNoteId ->{
            tagService.deleteTagLink(bookNoteId,ModuleEnum.BOOK_NOTE.getValue());
        });
        baseMapper.deleteBatchIds(Arrays.asList(bookNoteIds));
    }

    @Override
    public void updateBookNote(BookNoteDTO bookNote) {
        // 删除多对多所属标签
        tagService.deleteTagLink(bookNote.getId(),ModuleEnum.BOOK_NOTE.getValue());
        // 更新所属标签
        tagService.saveTagOrInsert(bookNote.getTagList(),bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
        // 更新笔记
        baseMapper.updateById(bookNote);
    }

    @Override
    public BookNoteDTO getBookNote(Integer bookNoteId) {
        BookNoteDTO bookNoteDTO = null;

        BookNote bookNote = baseMapper.selectById(bookNoteId);

        if(bookNote!=null){
            bookNoteDTO = new BookNoteDTO();
            BeanUtils.copyProperties(bookNote,bookNoteDTO);
            bookNoteDTO.setTagList(tagService.listByLinkId(bookNoteDTO.getId(),ModuleEnum.BOOK_NOTE.getValue()));
        }


        return bookNoteDTO;
    }

    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }
}
