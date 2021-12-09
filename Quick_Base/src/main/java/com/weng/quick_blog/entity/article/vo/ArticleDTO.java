/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.entity.article.vo;

import com.weng.quick_blog.entity.article.Article;
import com.weng.quick_blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version articleDTO:ArticleDTO.java v1.0 2021/12/2 4:14 下午 wengchengjian Exp $
 */
@Data
public class ArticleDTO extends Article {
    private List<Tag> tagList;
}
