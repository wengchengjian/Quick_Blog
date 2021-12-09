/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service;

import com.weng.quick_blog.service.article.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *  ArticleService 测试类
 * </p>
 * @author wengchengjian
 * @version articleServiceTest:ArticleServiceTest.java v1.0 2021/12/2 5:47 下午 wengchengjian Exp $
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Test
    public void checkCategory(){
        assert articleService.checkByCategory(11);
    }
}
