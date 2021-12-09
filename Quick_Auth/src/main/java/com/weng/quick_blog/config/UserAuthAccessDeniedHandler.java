/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ErrorEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  暂无权限处理类
 * </p>
 * @author wengchengjian
 * @version UserAuthAccessDeniedHandler:UserAuthAccessDeniedHandler.java v1.0 2021/12/8 4:40 下午 wengchengjian Exp $
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result.Failure(response, ErrorEnum.NO_AUTH);
    }
}
