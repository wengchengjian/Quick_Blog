/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.config.core;

import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.service.sys.SysMenuService;
import com.weng.quick_blog.service.sys.SysPermService;
import com.weng.quick_blog.service.sys.SysRolePermService;
import com.weng.quick_blog.service.sys.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version UserPermissionEvaluator:UserPermissionEvaluator.java v1.0 2021/12/8 5:48 下午 wengchengjian Exp $
 */
@Component
@Slf4j
public class UserPermissionEvaluator implements PermissionEvaluator {


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRolePermService sysRolePermService;

    @Autowired
    private SysPermService sysPermService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.info("targetDomainObject: {}, permission: {}",targetDomainObject,permission);

        // 通过上下文获取当前用户
        SafeUserDetails userInfo = (SafeUserDetails) authentication.getPrincipal();

        // 获取当前用户的权限，这个权限是JWTFilter在拦截时重新查询的权限，勉强算即时权限
        Collection<SysPerm> sysPerms = userInfo.getPermissions();
        // map映射SysPerm的权限名
        Set<String> permissions= sysPerms.stream().map(sysPerm -> sysPerm.getName()).collect(Collectors.toSet());
        log.info("当前用户的权限: {}",permissions);

        return  permissions.contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }


}
