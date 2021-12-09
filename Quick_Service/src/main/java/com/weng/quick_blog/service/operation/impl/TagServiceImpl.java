/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Tag;
import com.weng.quick_blog.entity.operation.TagLink;
import com.weng.quick_blog.mapper.operation.TagLinkMapper;
import com.weng.quick_blog.mapper.operation.TagMapper;
import com.weng.quick_blog.service.operation.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version tagServiceImpl:TagServiceImpl.java v1.0 2021/12/2 2:39 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Resource
    private TagLinkMapper tagLinkMapper;

    /**
     * 分页查询
     * @param pageNum   当前页
     * @param pageSize  页大小
     * @return
     */
    @Override
    public PageQuery<Tag> queryPage(Integer pageNum, Integer pageSize) {
        Page<Tag> page = new Page<>(pageNum,pageSize);
        baseMapper.selectPage(page,new QueryWrapper<Tag>().lambda());
        PageQuery<Tag> pageQuery = new PageQuery<>(page);
        return pageQuery;
    }

    /**
     * 根据关联id获取列表
     * @param linkId
     * @param type
     * @return
     */
    @Override
    public List<Tag> listByLinkId(Integer linkId, Integer type) {
        return baseMapper.listByLinkId(linkId,type);
    }

    /**
     * 添加所属标签，包含新增标签
     * @param tagList
     * @param linkId
     * @param type
     */
    @Override
    public void saveTagOrInsert(List<Tag> tagList, Integer linkId, Integer type) {
        Optional.ofNullable(tagList)
                .ifPresent(tagListValue->tagListValue
                        .forEach(item->{
                            if(item.getId()==null){
                                baseMapper.insert(item);
                            }
                            TagLink tagLink = new TagLink(linkId,item.getId(),type);
                            tagLinkMapper.insert(tagLink);
                        }));
    }

    /**
     * 删除tagLink关联
     * @param linkId
     * @param type
     */
    @Override
    public void deleteTagLink(Integer linkId, Integer type) {
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                .eq(linkId!=null,TagLink::getLinkId,linkId)
                .eq(type!=null,TagLink::getType,type));
    }
}
