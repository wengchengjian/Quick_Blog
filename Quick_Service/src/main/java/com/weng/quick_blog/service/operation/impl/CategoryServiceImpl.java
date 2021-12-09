/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.operation.Category;
import com.weng.quick_blog.mapper.operation.CategoryMapper;
import com.weng.quick_blog.service.operation.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryServiceImpl:CategoryServiceImpl.java v1.0 2021/12/1 9:57 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> queryWithParentName(String parentName,Integer type) {
        return baseMapper.queryAll(parentName,type);
    }

    @Override
    public List<Category> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<Category> queryWithParenId(Integer parentId) {
        return baseMapper.selectList(new QueryWrapper<Category>()
                .lambda().eq(Category::getParentId,parentId));
    }

    @Override
    public String queryCategoryArr(String categoryIds) {


        List<String> ids = Arrays.asList(categoryIds.split(","));

        StringBuffer list = new StringBuffer();

        for(int i = 0;i<ids.size();i++){

            String id = ids.get(i);

            Category category = baseMapper.selectById(id);

            list.append(category.getName());
        }
        return list.toString();
    }
}
