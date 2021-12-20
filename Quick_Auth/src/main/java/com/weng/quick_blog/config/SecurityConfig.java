/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config;

import com.weng.quick_blog.config.core.UserAuthenticationProvider;
import com.weng.quick_blog.config.core.UserPermissionEvaluator;
import com.weng.quick_blog.config.filter.CustomAuthenticationFilter;
import com.weng.quick_blog.config.filter.JWTAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SecurityConfig:SecurityConfig.java v1.0 2021/12/8 2:40 下午 wengchengjian Exp $
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTConfig jwtConfig;
    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;

    /**
     * 自定义未登陆处理器
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationFilter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(userLoginFailureHandler);

        return customAuthenticationFilter;
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(jwtConfig.getAntMatchers().split(",")).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                .cors()
                .and()
                .csrf().disable();
        // 基于token则不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        //替换原表单登录方式
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加JWT过滤器
        http.addFilter(jwtAuthenticationTokenFilter());


    }

    @Bean
    public  JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        return new JWTAuthenticationTokenFilter(authenticationManager());
    }
}
