/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.vo.RecommendVO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version recommenServic:RecommendService.java v1.0 2021/12/2 7:42 下午 wengchengjian Exp $
 */
public interface RecommendService extends IService<Recommend> {

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<Recommend> queryPage(Integer pageNum,Integer pageSize,String title);

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 更新置顶状态
     * @param id
     */
    void updateTop(Integer id);

    /**
     * 批量删除
     * @param linkId
     * @param type
     */
    void deleteBatchByLinkId(Integer[] linkId,Integer type);
}
