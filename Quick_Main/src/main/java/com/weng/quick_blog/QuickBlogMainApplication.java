/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version mainApplication:QuickBlogMainApplication.java v1.0 2021/12/1 7:13 下午 wengchengjian Exp $
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.weng.quick_blog.mapper.*"})
public class QuickBlogMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickBlogMainApplication.class,args);
    }
}
