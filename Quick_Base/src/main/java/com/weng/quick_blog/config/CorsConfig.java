/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CorsConfig:CorsConfig.java v1.0 2021/12/16 4:04 下午 wengchengjian Exp $
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configure cross origin requests processing.
     * @since 4.2
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                //            .allowedOrigins("http://yyy.com", "http://xxx.com") //
                // 允许跨域的域名
                .allowedOrigins("*")
                .allowedMethods("*") // 允许任何方法（post、get等）
                .allowedHeaders("*") // 允许任何请求头
                .allowCredentials(true) // 允许证书、cookie
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .maxAge(3600L); // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
    }
}
