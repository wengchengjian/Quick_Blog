/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.sys;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.service.sys.SysRolePermService;
import com.weng.quick_blog.service.sys.SysRoleService;
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
 * @version SysRoleController:SysRoleController.java v1.0 2021/12/10 5:41 下午 wengchengjian Exp $
 */
@Slf4j
@RestController
@RequestMapping("/admin/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermService sysRolePermService;

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/sys/role/list','sys:role:list')")
    public Result<List<SysRole>> list(){

    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/sys/role/info','sys:role:info')")
    public Result<SysRole> info(){

    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/sys/role/save','sys:role:save')")
    public Result save(){

    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/sys/role/update','sys:role:update')")
    public Result update(){

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/sys/role/delete','sys:role:delete')")
    public Result delete(){

    }
}
