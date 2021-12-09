/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.sys.SysMenu;
import com.weng.quick_blog.mapper.sys.SysMenuMapper;
import com.weng.quick_blog.service.sys.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<SysMenu> listUserMenu(Integer userId) {
        return null;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        return null;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId) {
        return null;
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return null;
    }

    @Override
    public List<SysMenu> getUserMenuList(Integer userId) {
        return null;
    }

    @Override
    public void delete(Integer menuId) {

    }
}
