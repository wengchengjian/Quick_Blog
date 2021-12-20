/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.sys;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.request.PasswordRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.sys.SysUser;
import com.weng.quick_blog.entity.sys.vo.SysUserVO;
import com.weng.quick_blog.service.sys.SysRoleService;
import com.weng.quick_blog.service.sys.SysUserRoleService;
import com.weng.quick_blog.service.sys.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysUserController:SysUserController.java v1.0 2021/12/13 11:09 上午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取登录信息
     * @return
     */
    @GetMapping("/info")
    @PreAuthorize("hasPermission('/sys/user/info','sys:user:info')")
    public Result<SysUserVO> getInfo() {
        SysUserVO currentUser = sysUserService.getCurrentUser();

        return Result.Success(currentUser);
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('/sys/user/list','sys:user:list')")
    public Result<PageQuery<SysUserVO>> list(@RequestParam(value = "pageNum",defaultValue = "0")Integer pageNum,
                                           @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize,
                                           @RequestParam(value="userName",defaultValue = "")String userName
                                           ) {
        SysUserVO currentUser = sysUserService.getCurrentUser();
        PageQuery<SysUserVO> res = null;
        if(sysUserService.isAdmin()){
            res=sysUserService.queryPage(pageNum,pageSize,userName);
        }else{
            res=sysUserService.queryPage(pageNum,pageSize,userName,currentUser.getUserId());
        }
        return Result.Success(res);
    }

    /**
     * 更新密码
     * @param request
     * @return
     */
    @PutMapping("/password")
    @PreAuthorize("hasPermission('/sys/user/update','sys:user:update')")
    public Result password(@RequestBody PasswordRequest request){
        if(StringUtils.isEmpty(request.getOldPassword()) ||
           StringUtils.isEmpty(request.getNewPassword()) ){
            return Result.Failure("密码不能为空");
        }
        Boolean update =sysUserService.updatePassword(request.getOldPassword(),request.getNewPassword());
        if(!update){
            return Result.Failure("原密码错误");
        }
        return Result.Success();
    }

    @PostMapping("/save")
    @PreAuthorize("hasPermission('/sys/user/save','sys:user:save')")
    public Result save(@RequestBody SysUser sysUser){
        SysUserVO currentUser = sysUserService.getCurrentUser();

        sysUser.setCreateUserId(currentUser.getUserId());

        sysUserService.save(sysUser);

        //TODO 更新用户与角色的关联

        return Result.Success();
    }

    @PutMapping("/update")
    @PreAuthorize("hasPermission('/sys/user/update','sys:user:update')")
    public Result update(@RequestBody SysUser sysUser){

        sysUserService.updateById(sysUser);

        //TODO 更新用户与角色的关联


        return Result.Success();
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/sys/user/delete','sys:user:delete')")
    public Result delete(@RequestBody Integer[] ids){

        sysUserService.deleteBatch(ids);

        return Result.Success();

    }

    /**
     * 只有用户的创建者 或者管理员 才能查看
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/sys/user/info','sys:user:info')")
    public Result<SysUserVO> info(@PathVariable("id")Integer id){
        SysUserVO currentUser = sysUserService.getCurrentUser();

        SysUserVO byId = sysUserService.infoById(id);

        if(byId.getCreateUserId().equals(currentUser.getUserId()) || sysUserService.isAdmin()){
            return Result.Success(byId);
        }else{
            return Result.Failure("您不是该用户的创建者，无权查看");
        }
    }


}
