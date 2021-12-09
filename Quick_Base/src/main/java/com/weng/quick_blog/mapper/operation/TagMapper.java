/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.operation.Tag;
import com.weng.quick_blog.entity.operation.vo.TagVO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version tagMapper:TagMapper.java v1.0 2021/12/2 2:16 下午 wengchengjian Exp $
 */
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> listByLinkId(Integer linkId,Integer type);

    Integer deleteTagLink(Integer linkId,Integer type);

    List<TagVO> listTagVo();
}
