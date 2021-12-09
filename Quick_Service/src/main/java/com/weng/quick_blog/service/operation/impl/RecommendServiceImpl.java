/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.vo.RecommendVO;
import com.weng.quick_blog.mapper.operation.RecommendMapper;
import com.weng.quick_blog.service.operation.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version RecommendServiceImpl:RecommendServiceImpl.java v1.0 2021/12/2 7:43 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
    @Override
    public PageQuery<Recommend> queryPage(Integer pageNum, Integer pageSize,String title) {
        IPage<Recommend> page= baseMapper.selectPage(new Page(pageNum,pageSize),
                new QueryWrapper<Recommend>().lambda().like(title!=null,Recommend::getTitle,title));

        return new PageQuery<Recommend>(page);
    }

    @Override
    public List<RecommendVO> listSelect() {
        return baseMapper.listSelect();
    }

    @Override
    public void updateTop(Integer id) {
        //自己置顶，其他取消置顶
        baseMapper.becomeTop(id);
        baseMapper.otherDecomeTop(id);
    }

    @Override
    public void deleteBatchByLinkId(Integer[] linkId, Integer type) {

    }
}
