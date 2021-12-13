/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.sys;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysMenu;
import com.weng.quick_blog.service.sys.SysMenuService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysMenuController:SysMenuController.java v1.0 2021/12/10 3:16 下午 wengchengjian Exp $
 */
@Slf4j
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/nav")
    @PreAuthorize("hasPermission('/sys/menu/list','sys:menu:list')")
    public Result<List<SysMenu>> getUserMenu(){
        List<SysMenu> sysMenus = sysMenuService.listUserMenu();

        return Result.Success(sysMenus);
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/sys/menu/list','sys:menu:list')")
    public Result<List<SysMenu>> list(){
        List<SysMenu> sysMenus = sysMenuService.list(null);


        return Result.Success(sysMenus);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/sys/menu/info','sys:menu:info')")
    public Result<SysMenu> info(@PathVariable("id")Integer id){
        SysMenu sysMenus = sysMenuService.getById(id);

        return Result.Success(sysMenus);
    }

    @GetMapping("/save")
    @PreAuthorize("hasPermission('/sys/menu/save','sys:menu:save')")
    public Result save(@RequestBody SysMenu menu){
        sysMenuService.save(menu);

        return Result.Success();
    }

    @GetMapping("/update")
    @PreAuthorize("hasPermission('/sys/menu/update','sys:menu:update')")
    public Result update(@RequestBody SysMenu menu){

        sysMenuService.updateById(menu);

        return Result.Success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/sys/menu/delete','sys:menu:delete')")
    public Result delete(@RequestBody Integer[] ids){
        SafeUserDetails user = sysUserService.getCurrentUser();

        for(Integer id: ids){
            Boolean has = sysMenuService.hasChildrenMenu(id);
            SysMenu menu = sysMenuService.getById(id);
            if(has){
                return Result.Failure("请先删除子菜单");
            }else{
                sysMenuService.delete(id);
                log.info("{} 删除了子菜单: {}",user.getUsername(),menu.getName());
            }
        }
        return Result.Success();
    }

}
