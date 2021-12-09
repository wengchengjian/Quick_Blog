/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.security;

import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.entity.sys.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SafeUserDetails:SafeUserDetails.java v1.0 2021/12/8 4:02 下午 wengchengjian Exp $
 */
@Data
public class SafeUserDetails extends SysUser implements UserDetails , Serializable {

    private Collection<? extends GrantedAuthority> authorities;

    private Collection<SysPerm> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountExpired() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getLocked() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getPasswordExpired() == 0;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled() == 1;
    }
}
