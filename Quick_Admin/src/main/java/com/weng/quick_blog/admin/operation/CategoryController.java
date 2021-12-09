/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.operation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.CategoryRankEnum;
import com.weng.quick_blog.entity.operation.Category;
import com.weng.quick_blog.service.operation.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@PreAuthorize("hasRole('admin')")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/listByName")
    @PreAuthorize("hasPermission('/operation/category/list','operation:category:list')")
    public Result<List<Category>> listByName(@RequestParam String name,@RequestParam(value="type",defaultValue="1") Integer type){
        List<Category> categoryList = categoryService.queryWithParentName(name,type);
        return Result.Success(categoryList);
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/operation/category/list','operation:category:list')")
    public Result<List<Category>> list(){
        List<Category> categoryList = categoryService.queryAll();
        return Result.Success(categoryList);
    }

    @GetMapping("/select")
    @PreAuthorize("hasPermission('/operation/category/list','operation:category:list')")
    public Result<List<Category>> select(@RequestParam(value="type",defaultValue="1") Integer type){
        List<Category> list = categoryService.list(new QueryWrapper<Category>()
                .lambda().eq(Category::getType,type));
        // 添加顶级分类
        Category root = new Category();
        root.setId(-1);
        root.setName("根目录");
        root.setParentId(-1);
        list.add(root);
        return Result.Success(list);
    }
    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/operation/category/info','operation:category:info')")
    public Result<Category> info(@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);
        return Result.Success(category);
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/operation/category/save','operation:category:save')")
    public Result save(@RequestBody Category category) throws Exception {
        //TODO 数据校验

        verifyCategory(category);
        categoryService.save(category);
        return Result.Success(null);
    }

    private void verifyCategory(Category category) throws Exception {

        int parentRank  = CategoryRankEnum.ROOT.getValue();

        if(category.getParentId() !=CategoryRankEnum.FIRST.getValue()
           && category.getParentId() != CategoryRankEnum.ROOT.getValue()){
            Category parentCategory = categoryService.getById(category.getParentId());
            parentRank = parentCategory.getRank();
        }

        //TODO 替换自定义异常类型
        // 一级
        if(category.getRank() == CategoryRankEnum.FIRST.getValue()){
            if(category.getParentId() != CategoryRankEnum.ROOT.getValue()){
                throw new Exception("一级目录的上级目录只能是根目录");
            }
        }

        //二级
        if (category.getRank() == CategoryRankEnum.SECOND.getValue()) {
            if (parentRank != CategoryRankEnum.FIRST.getValue()) {
                throw new Exception("上级目录只能为一级类型");
            }
        }

        //三级
        if (category.getRank() == CategoryRankEnum.THIRD.getValue()) {
            if (parentRank != CategoryRankEnum.SECOND.getValue()) {
                throw new Exception("上级目录只能为二级类型");
            }
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/operation/category/update','operation:category:update')")
    public Result update(@RequestBody Category category){
        //TODO 参数校验
        categoryService.updateById(category);
        return Result.Success(null);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasPermission('/operation/category/delete','operation:category:delete')")
    public Result delete(@PathVariable("id")Integer id){
        //判断是否有子菜单
        List<Category> list = categoryService.queryWithParenId(id);

        //TODO 判断是否关联文章

        //TODO 判断是否关联图书

        //TODO 判断是否关联笔记

        categoryService.removeById(id);

        return Result.Success(null);

    }
}
