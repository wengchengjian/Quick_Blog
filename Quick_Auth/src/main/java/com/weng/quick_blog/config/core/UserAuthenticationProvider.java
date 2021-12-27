/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config.core;

import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.service.security.SafeUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version UserAuthenticationProvider:UserAuthenticationProvider.java v1.0 2021/12/8 5:07 下午 wengchengjian Exp $
 */
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SafeUserDetailsService safeUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中返回的密码
        String password = (String) authentication.getCredentials();

        log.info("username:{},password:{}",userName,password);
        SafeUserDetails userInfo = (SafeUserDetails) safeUserDetailsService.loadUserByUsername(userName);


        if(userInfo==null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        if(!bCryptPasswordEncoder.matches(password,userInfo.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        // TODO 替换常量
        if(userInfo.getLocked().equals("PROHIBIT")){
            throw new LockedException("用户已被冻结");
        }

        return  new UsernamePasswordAuthenticationToken(userInfo,password,userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
