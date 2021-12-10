/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.book;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.entity.book.BookSense;
import com.weng.quick_blog.service.book.BookSenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  权限还没加
 * </p>
 * @author wengchengjian
 * @version BookSenseController:BookSenseController.java v1.0 2021/12/6 4:30 下午 wengchengjian Exp $
 */
@Slf4j
@RestController
@RequestMapping("/admin/book/sense")
public class BookSenseController {
    @Autowired
    private BookSenseService bookSenseService;

    @GetMapping("/info/{bookId}")
    public Result<BookSense> info(@PathVariable("bookId")Integer id){
        BookSense bookSense = bookSenseService.getBookSense(id);
        return Result.Success(bookSense);
    }
    @PostMapping("/save")
    public Result save(@RequestBody BookSense bookSense){
        //TODO 参数验证
        bookSenseService.save(bookSense);
        return Result.Success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody BookSense bookSense){
        //TODO 参数验证
        bookSenseService.updateById(bookSense);
        return Result.Success();
    }
}
