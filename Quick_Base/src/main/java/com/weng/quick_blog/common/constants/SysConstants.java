/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.constants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysConstants:SysConstans.java v1.0 2021/12/8 11:05 上午 wengchengjian Exp $
 */
public class SysConstants {
    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_admin");
}
