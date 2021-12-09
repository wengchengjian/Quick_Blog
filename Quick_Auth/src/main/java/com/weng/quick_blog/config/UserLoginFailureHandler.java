/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  用户登录失败
 * </p>
 * @author wengchengjian
 * @version UserLoginFailureHandler:UserLoginFailureHandler.java v1.0 2021/12/8 4:51 下午 wengchengjian Exp $
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】: {}",exception.getMessage());
            Result.Failure(response, ErrorEnum.USER_NOT_FOUND);
        }else if(exception instanceof LockedException) {
            log.info("【登录失败】: {}",exception.getMessage());
            Result.Failure(response,ErrorEnum.USER_FREEZE);
        }else if(exception instanceof BadCredentialsException){
            log.info("【登录失败】: {}",exception.getMessage());
            Result.Failure(response,ErrorEnum.USER_PASSWORD_FAIL);
        }else{
            Result.Failure(response,ErrorEnum.LOGIN_FAIL);
        }

    }
}
