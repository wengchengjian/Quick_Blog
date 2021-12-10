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
     * @return
     */
    List<SysMenu> listUserMenu();

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
     * 删除
     * @param menuId
     */
    void delete(Integer menuId);

    /**
     * 查询是否有子菜单
     * @param id
     * @return
     */
    Boolean hasChildrenMenu(Integer id);

    /**
     * 简单逻辑 - 根据ids列表查询菜单
     * @param menuIds
     * @return
     */
    List<SysMenu> queryListByBatchIds(List<Integer> menuIds);

    List<SysMenu> queryAllMenuByPath(Integer menuId);
}
