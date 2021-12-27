/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service;

import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.service.operation.LinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version linkServiceTest:LinkServiceTest.java v1.0 2021/12/1 8:10 下午 wengchengjian Exp $
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LinkServiceTest {
    @Resource
    private LinkService linkService;

    @Test
    public void queryPage() {
        linkService.removeById(5);
    }
}
