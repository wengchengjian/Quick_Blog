/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysMenu;
import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.mapper.sys.SysMenuMapper;
import com.weng.quick_blog.service.sys.SysMenuService;
import com.weng.quick_blog.service.sys.SysPermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysMenuService:SysMenuServiceImpl.java v1.0 2021/12/7 2:39 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysPermService sysPermService;

    @Override
    public List<SysMenu> listUserMenu() {
        SecurityContext context = SecurityContextHolder.getContext();
        SafeUserDetails user = (SafeUserDetails) context.getAuthentication().getPrincipal();
        //获取当前用户的权限
        Collection<SysPerm> permissions = user.getPermissions();

        log.info("当前用户的权限对象列表:{}",permissions);
        Set<SysMenu> set = new HashSet<>();


        List<SysMenu> sysMenus = sysPermService.queryMenuByPerm(permissions);
        set.addAll(sysMenus);

        return new ArrayList<SysMenu>(set);
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        return null;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }


    @Override
    public void delete(Integer menuId) {

    }

    @Override
    public Boolean hasChildrenMenu(Integer id) {
        return Optional.ofNullable(queryListParentId(id)).get().size() > 0;
    }

    @Override
    public List<SysMenu> queryListByBatchIds(List<Integer> menuIds) {
        return baseMapper.selectBatchIds(menuIds);
    }

    @Override
    public List<SysMenu> queryAllMenuByPath(Integer menuId) {
        Integer currentId = menuId;
        List<SysMenu> res = new ArrayList<>();
        while(currentId!=0){
            SysMenu sysMenu = baseMapper.selectOne(
                    new QueryWrapper<SysMenu>().lambda().eq(currentId != null, SysMenu::getMenuId, currentId));
            currentId = sysMenu.getParentId();
            res.add(sysMenu);
        }
        return res;
    }
}
