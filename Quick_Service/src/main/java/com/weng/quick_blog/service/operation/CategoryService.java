/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation;

import com.baomidou.mybatisplus.extension.service.IService;
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
}
