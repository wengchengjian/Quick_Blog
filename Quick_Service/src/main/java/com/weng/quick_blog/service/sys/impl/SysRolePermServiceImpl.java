/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.sys.SysRolePerm;
import com.weng.quick_blog.mapper.sys.SysRolePermMapper;
import com.weng.quick_blog.service.sys.SysRolePermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysRolePermServiceImpl:SysRolePermServiceImpl.java v1.0 2021/12/9 10:42 上午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysRolePermServiceImpl extends ServiceImpl<SysRolePermMapper, SysRolePerm> implements SysRolePermService {
    @Override
    public List<Integer> queryAllPermsIdsByRoleId(Integer roleId) {

        List<SysRolePerm> sysRolePerms = baseMapper.selectList(
                new QueryWrapper<SysRolePerm>().lambda()
                        .eq(roleId != null, SysRolePerm::getRoleId, roleId));

        return sysRolePerms.stream().map(sysRolePerm-> sysRolePerm.getPermId()).collect(Collectors.toList());
    }

    /**
     * 批量删除权限和角色的关联
     * @param roleIds
     */
    @Override
    public void delteBatchByRoleId(List<Integer> roleIds) {
        baseMapper.delete(new UpdateWrapper<SysRolePerm>().lambda()
                .in(roleIds!=null && roleIds.size() > 0,SysRolePerm::getRoleId,roleIds));
    }

    /**
     * 删除权限和角色的关联
     * @param roleId
     */
    @Override
    public void delteByRoleId(Integer roleId) {
        baseMapper.delete(new UpdateWrapper<SysRolePerm>().lambda()
                .eq(roleId!=null,SysRolePerm::getRoleId,roleId));
    }
}
