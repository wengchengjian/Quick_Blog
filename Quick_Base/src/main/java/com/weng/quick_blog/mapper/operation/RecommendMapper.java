/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.vo.RecommendVO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version recommendMapper:RecommendMapper.java v1.0 2021/12/2 3:51 下午 wengchengjian Exp $
 */
public interface RecommendMapper extends BaseMapper<Recommend> {
    /**
     * 获取推荐文章
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listRecommendVO();

    /**
     * 获取最热列表
     * @return
     */
    List<Recommend> listHotRead();

}
