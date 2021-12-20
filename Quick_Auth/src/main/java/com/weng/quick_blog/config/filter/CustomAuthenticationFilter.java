/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weng.quick_blog.common.request.LoginRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  替换为json格式的数据来登录
 * </p>
 * @author wengchengjian
 * @version CustomAuthenticationFilter:CustomAuthenticationFilter.java v1.0 2021/12/14 10:52 上午 wengchengjian Exp $
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 判断请求体是否是json数据
        if ((request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE))
            || (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))) {
            // 用来封装JSON数据
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (ServletInputStream inputStream = request.getInputStream()) {
                // 从数据流中读取JSON数据并封装到map中
                LoginRequest authenticationBean = mapper.readValue(inputStream, LoginRequest.class);
                authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),
                        authenticationBean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }

        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
