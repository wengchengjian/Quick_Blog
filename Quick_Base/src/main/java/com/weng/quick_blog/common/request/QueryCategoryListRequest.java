package com.weng.quick_blog.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 翁丞健
 * @Date 2021/12/25 16:45
 * @Version 1.0.0
 */
@Data
public class QueryCategoryListRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID =1L;

    private String name="";

    private Integer parentId =-1;
}
