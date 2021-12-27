/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config.filter;

import com.weng.quick_blog.config.JWTConfig;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.service.security.SafeUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version :JWTAuthenticationTokenFilter.java v1.0 2021/12/8 6:56 下午 wengchengjian Exp $
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private SafeUserDetailsService safeUserDetailsService;


    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager,
                                        AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String tokenHeader = request.getHeader(jwtConfig.tokenHeader);
        log.info("jwtConfig :{}",jwtConfig);
        if(tokenHeader!=null && tokenHeader.startsWith(jwtConfig.tokenPrefix)){
            try{
                String token  = tokenHeader.replace(jwtConfig.tokenPrefix,"");
                Claims  claims = Jwts.parser()
                        .setSigningKey(jwtConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                String userId = claims.getId();

                if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(userId)){

                    SafeUserDetails user = (SafeUserDetails) safeUserDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user,user.getId(),user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }catch(ExpiredJwtException e){
                log.info("Token过期 : {}",tokenHeader);
            }catch (Exception e){
                log.info("Token无效 :{} 错误原因:{}",tokenHeader,e.getMessage());
            }
        }

        chain.doFilter(request,response);
    }
}
