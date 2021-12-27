/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.request.QueryCategoryListRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Category;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryService:CategoryService.java v1.0 2021/12/1 9:53 下午 wengchengjian Exp $
 */
public interface CategoryService extends IService<Category> {
    /**
     * 查询所有菜单根据父类名
     * @param parentName
     * @return
     */
    List<Category> queryWithParentName(String parentName,Integer type);

    /**
     * 查询当前分类下的所有子分类(包括子孙分类)，广度遍历查询
     * @param id
     * @return
     */
    List<Category> queryCategoryById(Integer id);

    List<Category> queryAll();
    /**
     * 查询所有菜单根据父类id
     * @param parentId
     * @return
     */
    List<Category> queryWithParenId(Integer parentId);

    /**
     * 根据类别数组查询分类数组
     * @param categoryIds
     * @return
     */
    String queryCategoryArr(String categoryIds);

    List<Category> queryByName(String name);

    PageQuery<Category> queryPage(QueryCategoryListRequest request);

    /**
     * 查询是否有文章跟该分类关联
     * @param id 分类id
     * @return
     */
    boolean linkWithArticle(Integer id);

    /**
     * 查询是否有图书跟该分类关联
     * @param id 分类id
     * @return
     */
    boolean linkWithBook(Integer id);

    /**
     * 查询是否有笔记跟该分类关联
     * @param id 分类id
     * @return
     */
    boolean linkWithBookNote(Integer id);

    /**
     * 查询当前分类的父类是否存在
     * @param parentId
     * @return
     */
    Category findByParentId(Integer parentId);

    /**
     * 检查当前分类的合法性
     * @param category
     * @return
     */
    boolean checkCurrentCategory(Category category);
}
