/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Link;
import com.weng.quick_blog.mapper.operation.LinkMapper;
import com.weng.quick_blog.service.operation.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version linkServiceImpl:LinkServiceImpl.java v1.0 2021/12/1 7:24 下午 wengchengjian Exp $
 */
@Slf4j
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public PageQuery<Link> queryPage(Integer pageNum, Integer pageSize, String title){
        Page<Link> page = new Page<Link>(pageNum, pageSize);
        QueryWrapper<Link> queryWrapper = new QueryWrapper<Link>();

        IPage<Link> res =baseMapper.selectPage(page,queryWrapper.lambda().like(StringUtils.isNotEmpty(title),Link::getTitle,title));
        return new PageQuery(res);
    }
}
