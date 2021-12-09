/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service;

import com.weng.quick_blog.entity.operation.Category;
import com.weng.quick_blog.service.operation.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryServiceTest:CategoryServiceTest.java v1.0 2021/12/2 10:22 上午 wengchengjian Exp $
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {
    @Resource
    private CategoryService categoryService;

    @Test
    public void queryWithParentName(){
       List<Category> list = categoryService.queryWithParentName("JVM",1);
       System.out.println(list);
    }

    @Test
    public void queryWithParentId(){
        List<Category> list = categoryService.queryWithParenId(1);
        System.out.println(list);

    }
}
