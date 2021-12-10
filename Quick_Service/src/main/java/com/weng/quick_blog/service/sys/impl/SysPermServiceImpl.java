/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.entity.sys.SysMenu;
import com.weng.quick_blog.entity.sys.SysPerm;
import com.weng.quick_blog.mapper.sys.SysPermMapper;
import com.weng.quick_blog.service.sys.SysMenuService;
import com.weng.quick_blog.service.sys.SysPermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version SysPermServiceImpl:SysPermServiceImpl.java v1.0 2021/12/9 10:20 上午 wengchengjian Exp $
 */
@Service
@Slf4j
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements SysPermService {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public List<SysPerm> queryAllPerms(Integer permId) {
        List<SysPerm> res = new ArrayList<>();
        Integer currentId = permId;
        while(currentId!=0){
            SysPerm sysPerm = baseMapper
                    .selectOne(new QueryWrapper<SysPerm>().lambda()
                            .eq(currentId != 0, SysPerm::getId, currentId));
            res.add(sysPerm);
            currentId = sysPerm.getParentId();
        }
        return res;
    }

    @Override
    public List<SysMenu> queryMenuByPerm(Collection<? extends SysPerm> perms) {

        List<SysMenu> res = new ArrayList<>();
        perms.stream().forEach(perm->{
            List<SysMenu> menu = sysMenuService.queryAllMenuByPath(perm.getMenuId());
            res.addAll(menu);
        });
        return res;
    }
}
