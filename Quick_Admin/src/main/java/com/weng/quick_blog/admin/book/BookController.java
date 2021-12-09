/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.book;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.book.Book;
import com.weng.quick_blog.entity.book.dto.BookDTO;
import com.weng.quick_blog.entity.book.vo.BookVO;
import com.weng.quick_blog.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version bookController:BookController.java v1.0 2021/12/6 4:29 下午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/book")
@PreAuthorize("hasRole('admin')")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/list")
    @PreAuthorize("hasPermission('/read/book/list','book:list')")
    public Result<PageQuery<BookVO>> list(@RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "recommend",defaultValue="true") Boolean recommend,
                               @RequestParam(value = "favorite",defaultValue="true") Boolean favorite,
                               @RequestParam(value = "categoryId",defaultValue="") String categoryId,
                               @RequestParam(value = "latest",defaultValue="true") Boolean latest,
                               @RequestParam(value="title",defaultValue = "") String title){

        PageQuery<BookVO> res = bookService.queryPage(pageNum,pageSize,recommend,categoryId,latest,favorite,title);

        return Result.Success(res);
    }

    @GetMapping("/select")
    @PreAuthorize("hasPermission('/read/book/list','book:list')")
    public Result<List<Book>> select(){
        List<Book> bookList = bookService.list(null);
        return Result.Success(bookList);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/read/book/info','book:info')")
    public Result<BookDTO> info(@PathVariable("id") Integer id){
        BookDTO book = bookService.getBook(id);

        return Result.Success(book);
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/read/book/save','book:save')")
    public Result save(@RequestBody BookDTO book){
        //TODO 参数验证
        bookService.saveBook(book);
        return Result.Success(null);
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/read/book/update','book:update')")
    public Result update(@RequestBody BookDTO book){
        //TODO 参数验证
        bookService.updateBook(book);
        return Result.Success(null);
    }

    @PutMapping("/update/status")
    @PreAuthorize("hasPermission('/read/book/update','book:update')")
    public Result updateStatus(@RequestBody Book readBook){
        bookService.updateById(readBook);
        return Result.Success(null);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/read/book/delete','book:delete')")
    public Result delete(@RequestBody Integer[] ids){
        bookService.deleteBatch(ids);

        return Result.Success(null);
    }

}
