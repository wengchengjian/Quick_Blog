package com.weng.quick_blog.common.request.recommend;

import com.weng.quick_blog.common.request.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 翁丞健
 * @Date 2021/12/27 16:16
 * @Version 1.0.0
 */
@Data
public class QueryRecommendListPageRequest  extends PageRequest implements  Serializable {

    private static final long serialVersionUID =1L;

    /**
     * 推荐名称，可以文章title，图书title ,笔记title
     */
    private String title = "" ;

    /**
     * TODO 读后感推荐功能暂不实现，有待商榷
     * 推荐的类型 0:文章 1:图书,2:读书笔记,,-1:全部
     */
    private Integer type = -1;


    /**
     * 默认搜索指定内容 0：推荐，1：未推荐 ，-1：全部
     */
    private Integer top = 0;




}
