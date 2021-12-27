/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.operation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ModuleEnum;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Tag;
import com.weng.quick_blog.entity.operation.TagLink;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.mapper.operation.TagLinkMapper;
import com.weng.quick_blog.service.operation.TagService;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version TagController:TagController.java v1.0 2021/12/10 11:25 上午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/operation/tag")
@Slf4j
public class TagController {
    @Autowired
    private TagService tagService;


    @Autowired
    private TagLinkMapper tagLinkMapper;

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/operation/tag/list','operation:tag:list')")
    public Result<PageQuery<Tag>> list(@RequestParam(value="pageNum",defaultValue="0") Integer pageNum,
                                       @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value="name",defaultValue="") String name,
                                       @RequestParam(value="type",defaultValue="-1") Integer type){
        PageQuery<Tag> pageQuery = tagService.queryPage(pageNum, pageSize,name,type);
        return Result.Success(pageQuery);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/operation/tag/info','operation:tag:info')")
    public Result<Tag> info(@PathVariable(value="id",required = true)Integer id){
        Tag  tag = tagService.getById(id);
        return Result.Success(tag);
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/operation/tag/save','operation:tag:save')")
    public Result save(@RequestBody Tag tag){

        tagService.save(tag);
        return Result.Success();
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/operation/tag/update','operation:tag:update')")
    public Result update(@RequestBody Tag tag){
        tagService.updateById(tag);
        return Result.Success();
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/operation/tag/delete','operation:tag:delete')")
    public Result delete(@RequestBody Integer[] ids){
        SecurityContext context = SecurityContextHolder.getContext();
        SafeUserDetails user = (SafeUserDetails) context.getAuthentication().getPrincipal();
        for(Integer id: ids){

            Tag tag = tagService.getById(id);

            List<TagLink> tagLinks = tagLinkMapper
                    .selectList(new QueryWrapper<TagLink>().lambda()
                            .eq(id != null, TagLink::getTagId, id));
            if(!Collections.isEmpty(tagLinks)){
                TagLink tagLink = tagLinks.get(0);
                if(tagLink.getType() == ModuleEnum.ARTICLE.getValue()){
                    return Result.Failure("标签有文章引用,不能删除");
                }else if(tagLink.getType() == ModuleEnum.BOOK.getValue()){
                    return Result.Failure("标签有图书引用,不能删除");
                }else if(tagLink.getType() == ModuleEnum.BOOK_NOTE.getValue()){
                    return Result.Failure("标签有笔记引用,不能删除");
                }else{
                    return Result.Failure();
                }
            }else{
                tagService.removeById(id);
                log.info("{} 删除了标签: {} ",user.getUsername(),tag.getName());
            }
        }

        return Result.Success();
    }

}
