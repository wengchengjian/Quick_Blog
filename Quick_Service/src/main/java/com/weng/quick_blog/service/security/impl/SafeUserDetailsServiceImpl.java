/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.security.impl;

import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.service.security.SafeUserDetailsService;
import com.weng.quick_blog.service.sys.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SafeUserDetailsServiceImpl:SafeUserDetailsServiceImpl.java v1.0 2021/12/8 5:16 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SafeUserDetailsServiceImpl implements SafeUserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermService sysPermService;

    @Autowired
    private SysRolePermService sysRolePermService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new BadCredentialsException("用户名不能为空");
        }

        SysUser user = sysUserService.findByName(username);

        if(user==null){
            throw new UsernameNotFoundException("用户未找到");
        }

        SafeUserDetails userInfo = new SafeUserDetails();

        //拷贝数据
        BeanUtils.copyProperties(user,userInfo);

        Set<GrantedAuthority> roles = new HashSet<>();

        Set<SysPerm> permissions = new HashSet<>();

        // 查询用户所具有的角色id列表
        List<Integer> roleIds = sysUserRoleService.queryRoleIdList(userInfo.getUserId());

        Optional.ofNullable(roleIds).ifPresent(roleIdList->{
            roleIdList.forEach(roleId->{
                // 查询每个角色
                SysRole role = sysRoleService.findByRoleId(roleId);
                GrantedAuthority authority  = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
                roles.add(authority);

                //查询每个角色的权限
                List<Integer> permissionIds = sysRolePermService.queryAllPermsIdsByRoleId(roleId);

                log.info("permissionIds: {}",permissionIds);
                //set去重
                permissionIds.forEach(permissionId->{
                    List<SysPerm> sysPerms = sysPermService.queryAllPerms(permissionId);
                    permissions.addAll(sysPerms);
                });

            });
        });

        userInfo.setAuthorities(roles);
        userInfo.setPermissions(permissions);
        return userInfo;
    }
}
