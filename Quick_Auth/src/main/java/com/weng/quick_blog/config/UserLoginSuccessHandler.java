/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.util.JWTokenUtils;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  登录成功处理类
 * </p>
 * @author wengchengjian
 * @version UserLoginSuccessHandler:UserLoginSuccessHandler.java v1.0 2021/12/8 4:58 下午 wengchengjian Exp $
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTokenUtils jwTokenUtils;

    @Autowired
    private JWTConfig jwtConfig;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SafeUserDetails user = (SafeUserDetails) authentication.getPrincipal();
        // 生成token
        String token = jwTokenUtils.generateToken(user);

        // 组装token
        token = jwtConfig.tokenPrefix + token;

        // 发送token
        Result.Success(response,token);

    }
}
