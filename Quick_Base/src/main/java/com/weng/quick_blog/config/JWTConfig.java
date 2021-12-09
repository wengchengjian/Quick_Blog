/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version JWTConfig:JWTConfig.java v1.0 2021/12/8 4:24 下午 wengchengjian Exp $
 */
@Data
@ConfigurationProperties(prefix = "jwt",ignoreInvalidFields = true)
@Configuration
public class JWTConfig {

    public  long expiration;

    public  String  secret;

    public String tokenHeader;

    public String tokenPrefix;

    private  String antMatchers;
}
