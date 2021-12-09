/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.util;

import com.weng.quick_blog.config.JWTConfig;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version JwtUtils:JWTokenUtils.java v1.0 2021/12/8 4:21 下午 wengchengjian Exp $
 */
@Component
public class JWTokenUtils {

    @Autowired
    private JWTConfig jwtConfig;
    /**
     * 生成token
     * @param user
     * @return
     */
    public  final String generateToken(SafeUserDetails user){
        return Jwts.builder()
                            .setId(user.getUserId()+"")
                            .setSubject(user.getUsername())
                            //设置签发时间
                            .setIssuedAt(new Date())
                            .setIssuer("weng")
                            //自定义属性
//                            .claim("authorities",JsonUtils.toJson(user.getAuthorities()))
                            .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.expiration * 1000))
                            .signWith(SignatureAlgorithm.HS512,jwtConfig.secret)
                            .compact();
    }
}
