/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.operation.vo;

import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version recommendVO:RecommendVO.java v1.0 2021/12/2 3:48 下午 wengchengjian Exp $
 */
@Data
public class RecommendVO extends Recommend {
    private String description;

    private Long readNum;

    private Long commentNum;

    private Long likeNum;

    private String urlType;

    private String cover;

    private Date createTime;

    private List<Tag> tagList;
}
