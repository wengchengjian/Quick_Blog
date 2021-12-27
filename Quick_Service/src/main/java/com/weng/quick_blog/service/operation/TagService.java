/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Tag;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version tagServic:TagService.java v1.0 2021/12/2 2:35 下午 wengchengjian Exp $
 */
public interface TagService extends IService<Tag> {

    /**
     * 分页查询
     * @param pageNum   当前页
     * @param pageSize  页大小
     * @param name
     * @param type
     * @return
     */
    PageQuery<Tag> queryPage(Integer pageNum, Integer pageSize, String name, Integer type);

    /**
     * 通过关联id获取列表
     * @param linkId
     * @param type
     * @return
     */
    List<Tag> listByLinkId(Integer linkId, Integer type);

    /**
     * 添加所属标签，包含新增标签
     * @param tagList
     * @param linkId
     * @param type
     */
    void saveTagOrInsert(List<Tag> tagList,Integer linkId,Integer type);


    void deleteTagLink(Integer linkId,Integer type);
}
