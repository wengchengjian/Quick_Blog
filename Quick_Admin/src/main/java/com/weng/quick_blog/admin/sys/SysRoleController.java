/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.sys;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.entity.sys.SysRole;
import com.weng.quick_blog.service.sys.SysRolePermService;
import com.weng.quick_blog.service.sys.SysRoleService;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermService sysRolePermService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/sys/role/list','sys:role:list')")
    public Result<PageQuery<SysRole>> list(@RequestParam(value = "pageNum",defaultValue = "0")Integer pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
        SafeUserDetails user = sysUserService.getCurrentUser();
        PageQuery<SysRole> sysRolePageQuery = null;
        if(sysUserService.isAdmin()){
            //管理查询全部角色
            sysRolePageQuery = sysRoleService.queryPage(pageNum, pageSize);
        }else{
            //非管理员则只查询自己创建的角色
            sysRolePageQuery = sysRoleService.queryPage(pageNum, pageSize,user.getUserId());
        }
        return Result.Success(sysRolePageQuery);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/sys/role/info','sys:role:info')")
    public Result<SysRole> info(@PathVariable("id")Integer id){
        SafeUserDetails currentUser = sysUserService.getCurrentUser();
        SysRole byId = sysRoleService.getById(id);
        if(byId==null){
            return Result.Failure("无此用户");
        }
        // 如果是该角色的 创建者 或者是 管理员 则可以查询
        if(currentUser.getUserId().equals(byId.getCreateUserId()) || sysUserService.isAdmin()){
            return Result.Success(byId);
        }else{
            return Result.Failure("您不是该角色的创建者，无权查看");
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/sys/role/save','sys:role:save')")
    public Result save(@RequestBody  SysRole role){
        SafeUserDetails user = sysUserService.getCurrentUser();

        boolean exist = sysRoleService.isExist(role.getRoleName(),role.getCreateUserId());
        if(exist){
            return Result.Failure("该角色已经存在，请勿重复创建");
        }else{
            //保存角色
            sysRoleService.insert(role);
        }
        return Result.Success();
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/sys/role/update','sys:role:update')")
    public Result update(@RequestBody SysRole role){

        if(role.getRoleId()==null){
            return Result.Failure("更新的角色的id不能为空");
        }

        boolean exist = sysRoleService.isExist(role.getRoleName(),role.getCreateUserId());
        if(!exist){
            return Result.Failure("该角色不存在，无法更新");
        }else{
            sysRoleService.updateRole(role);
        }
        return Result.Success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/sys/role/delete','sys:role:delete')")
    public Result delete(@RequestBody Integer[] roleIds){
        log.info("删除的ids: {}", Arrays.toString(roleIds));
        sysRoleService.deleteBatch(roleIds);
        return Result.Success();
    }
}
