/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.operation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.CategoryRankEnum;
import com.weng.quick_blog.common.request.QueryCategoryListRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Category;
import com.weng.quick_blog.service.operation.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryController:CategoryController.java v1.0 2021/12/1 9:50 下午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/operation/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 分类选择器组件接口
     * @param name 分类名 支持模糊查询
     * @return
     */
    @GetMapping("/listByName")
    @PreAuthorize("hasPermission('/operation/category/list','operation:category:list')")
    public Result<List<Category>> listByName(@RequestParam(value="name",defaultValue = "") String name){
        List<Category> categoryList = categoryService.queryByName(name);
        return Result.Success(categoryList);
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/operation/category/list','operation:category:list')")
    public Result<PageQuery<Category>> list( QueryCategoryListRequest request){
        PageQuery<Category> categoryList = categoryService.queryPage(request);
        return Result.Success(categoryList);
    }


    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/operation/category/info','operation:category:info')")
    public Result<Category> info(@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);
        return Result.Success(category);
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/operation/category/save','operation:category:save')")
    public Result save(@Validated @RequestBody Category category) {
        //TODO 数据校验
        if(!verifyExist(category)){
            return Result.Failure("该分类的父分类不存在");
        }
        categoryService.save(category);

        return Result.Success();
    }

    private boolean verifyExist(@Validated Category category) {
        Category parent = categoryService.findByParentId(category.getParentId());
        if(parent==null){
            return false;
        }
        return true;
    }

    /**
     * 检查分类更新的合法性，主要检查当前更新的父节点是不是之前父节点的子节点，如果是则会形成闭环，更新失败
     * @param category
     * @return
     */
    private boolean verifyUpdate(@Validated Category category) {

        return categoryService.checkCurrentCategory(category);
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/operation/category/update','operation:category:update')")
    public Result update(@RequestBody Category category)  {
        //TODO 参数校验
        if(!verifyExist(category)){
            return Result.Failure("该分类的父分类不存在");
        }
        if(!verifyUpdate(category)){
            return Result.Failure("该分类的父分类不合法");
        }
        categoryService.updateById(category);
        return Result.Success(null);
    }


    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/operation/category/delete','operation:category:delete')")
    public Result delete(@RequestBody Integer[] ids){

        for(Integer id:ids){
            //判断是否有子分类
            List<Category> list = categoryService.queryWithParenId(id);

            if(list!=null&&list.size()!=0){
                return Result.Failure("该分类下仍还有子分类，请先删除子分类");
            }
            if(categoryService.linkWithArticle(id)){
                return Result.Failure("该分类下仍还有文章关联，请先删除文章");
            }
            if(categoryService.linkWithBook(id)){
                return Result.Failure("该分类下仍还有图书关联，请先删除图书");
            }
            if(categoryService.linkWithBookNote(id)){
                return Result.Failure("该分类下仍还有笔记关联，请先删除笔记");
            }
        }
        categoryService.removeByIds(Arrays.asList(ids));

        return Result.Success(null);


    }
}
