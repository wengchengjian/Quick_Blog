/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.article;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.article.Article;
import com.weng.quick_blog.entity.article.vo.ArticleDTO;
import com.weng.quick_blog.entity.article.vo.ArticleVO;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version ArticleService:ArticleService.java v1.0 2021/12/2 4:52 下午 wengchengjian Exp $
 */
public interface ArticleService extends IService<Article> {
    /**
     * 分页查询博客
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageQuery<ArticleVO> queryPage(Integer pageNum, Integer pageSize, String title);

    /**
     * 保存博客
     * @param article
     */
    void saveArticle(ArticleDTO article);

    /**
     * 批量删除
     * @param articleIds
     */
    void deleteBatch(Integer[] articleIds);

    /**
     * 更新博客
     * @param artilce
     */
    void updateArticle(ArticleDTO artilce);

    /**
     * 获取文章对象
     * @param articleId
     * @return
     */
    ArticleDTO getArticle(Integer articleId);

    Boolean checkByCategory(Integer id);

}
