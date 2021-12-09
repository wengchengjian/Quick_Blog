/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.admin.article;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.article.Article;
import com.weng.quick_blog.entity.article.vo.ArticleDTO;
import com.weng.quick_blog.entity.article.vo.ArticleVO;
import com.weng.quick_blog.service.article.ArticleService;
import com.weng.quick_blog.service.operation.RecommendService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version ArticleController:ArticleController.java v1.0 2021/12/2 5:57 下午 wengchengjian Exp $
 */
@RestController
@RequestMapping("/admin/article")
@PreAuthorize("hasRole('admin')")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @Resource
    private RecommendService recommendService;

    /**
     * 分页查询文章，还可以是对文章标题的模糊查询
     * @param pageNum
     * @param pageSize
     * @param title
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasPermission('/article/list','article:list')")
    public Result<PageQuery<ArticleVO>> list(@RequestParam(value = "pageNum",defaultValue ="0") Integer pageNum,
                                             @RequestParam(value = "pageSize",defaultValue ="10") Integer pageSize,
                                             @RequestParam(value = "title",defaultValue ="") String title){
        PageQuery<ArticleVO> pageQuery = articleService.queryPage(pageNum,pageSize,title);
        return Result.Success(pageQuery);
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasPermission('/article/info','article:info')")
    public Result<ArticleDTO> info(@PathVariable("id")Integer id){
        ArticleDTO article = articleService.getArticle(id);
        return Result.Success(article);
    }

    /**
     * 保存博客
     * TODO 没有做参数校验，待测试
     * @param article
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasPermission('/article/save','article:save')")

    public Result save(@RequestBody ArticleDTO article){
        //校验参数
        articleService.saveArticle(article);
        return Result.Success(null);
    }

    /**
     * 更新文章，更专注于全局的与文章有关的状态
     * TODO 没有做参数校验，待测试
     * @param article
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasPermission('/article/update','article:update')")
    public Result update(@RequestBody ArticleDTO article){
        articleService.updateArticle(article);
        return Result.Success(null);
    }

    /**
     * 也是更新文章，相对于前一个，它更专注于自身文章的更新，不会去做外键约束的更新
     * TODO 没有做参数校验，待测试
     * @param article
     * @return
     */
    @PutMapping("/update/status")
    @PreAuthorize("hasPermission('/article/update','article:update')")
    public Result updateStatus(@RequestBody Article article){
        articleService.updateById(article);
        return Result.Success(null);
    }

    /**
     * TODO 待测试
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasPermission('/article/delete','article:delete')")
    public Result delete(@RequestBody Integer[] ids){
        articleService.deleteBatch(ids);
        return Result.Success(null);
    }


}
