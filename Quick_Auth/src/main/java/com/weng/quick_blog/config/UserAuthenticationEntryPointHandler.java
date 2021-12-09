/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ErrorEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  用户没登录处理类
 * </p>
 * @author wengchengjian
 * @version UserAuthenticationEntryPointHandler:UserAuthenticationEntryPointHandler.java v1.0 2021/12/8 4:48 下午 wengchengjian Exp $
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Result.Failure(response, ErrorEnum.NOT_LOGIN);
    }
}
