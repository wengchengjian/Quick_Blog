/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.entity.sys.SysMenu;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysMenuService:SysMenuServiceImpl.java v1.0 2021/12/7 2:39 下午 wengchengjian Exp $
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 获取用户的所有菜单
     * @param userId
     * @return
     */
    List<SysMenu> listUserMenu(Integer userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenu> queryListParentId(Integer parentId,List<Integer> menuIdList);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenu> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> getUserMenuList(Integer userId);

    /**
     * 删除
     * @param menuId
     */
    void delete(Integer menuId);
}
