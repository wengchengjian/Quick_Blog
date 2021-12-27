/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.operation;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.request.QueryLinkListRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Link;
import com.weng.quick_blog.service.operation.LinkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version linkController:LinkController.java v1.0 2021/12/1 7:54 下午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/operation/link")
public class LinkController {
    @Resource
    private LinkService linkService;

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/operation/link/list','operation:link:list')")
    public Result<PageQuery<Link>> list( QueryLinkListRequest request){

        PageQuery<Link> page = linkService.queryPage(request);

        return Result.Success(page);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/operation/link/info','operation:link:info')")
    public Result<Link> info(@PathVariable("id") Integer id){
        Link link = linkService.getById(id);
        return Result.Success(link);
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/operation/link/save','operation:link:save')")
    public Result save(@Validated @RequestBody Link link){
        linkService.save(link);

        return Result.Success();
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/operation/link/update','operation:link:update')")
    public Result update(@Validated @RequestBody Link link){
        linkService.updateById(link);

        return Result.Success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/operation/link/delete','operation:link:delete')")
    public Result delete(@RequestBody Integer[] ids){

        linkService.removeByIds(Arrays.asList(ids));

        return Result.Success();
    }

}
