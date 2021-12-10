/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.book;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ModuleEnum;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.dto.BookNoteDTO;
import com.weng.quick_blog.entity.book.vo.BookNoteVO;
import com.weng.quick_blog.service.book.BookNoteService;
import com.weng.quick_blog.service.operation.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version BookNoteController:BookNoteController.java v1.0 2021/12/6 4:30 下午 wengchengjian Exp $
 */
@Slf4j
@RestController
@RequestMapping("/admin/book/note")
public class BookNoteController {
    @Autowired
    private BookNoteService bookNoteService;

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/read/note/list','book:note:list')")
    public Result<PageQuery<BookNoteVO>> list(@RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "recommend",defaultValue="true") Boolean recommend,
                                            @RequestParam(value = "favorite",defaultValue="true") Boolean favorite,
                                            @RequestParam(value = "categoryId",defaultValue="") String categoryId,
                                            @RequestParam(value = "latest",defaultValue="true") Boolean latest,
                                            @RequestParam(value = "title",defaultValue = "")String title){
        PageQuery<BookNoteVO> bookNoteVOPageQuery = bookNoteService
                .queryPage(pageNum, pageSize, recommend, categoryId, latest, favorite,title);

        return Result.Success(bookNoteVOPageQuery);

    }

    @PreAuthorize("hasPermission('/read/note/info','book:note:info')")
    @GetMapping("/info/{id}")
    public Result<BookNoteDTO> info(@PathVariable("id") Integer id){
        BookNoteDTO bookNote = bookNoteService.getBookNote(id);
        return Result.Success(bookNote);
    }

    @PreAuthorize("hasPermission('/read/note/save','book:note:save')")
    @PostMapping("/save")
    public Result save(@RequestBody BookNoteDTO bookNote){
        bookNoteService.saveBookNote(bookNote);
        return Result.Success();
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/read/note/update','book:note:update')")
    public Result update(@RequestBody BookNoteDTO bookNote) {
        //TODO 参数验证
        bookNoteService.updateBookNote(bookNote);
        return Result.Success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/read/note/delete','book:note:delete')")
    public Result deleteBatch(@RequestBody Integer[] bookNoteIds){
        recommendService.deleteBatchByLinkId(bookNoteIds, ModuleEnum.BOOK_NOTE.getValue());
        bookNoteService.deleteBatch(bookNoteIds);
        return Result.Success();
    }
}
