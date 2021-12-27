package com.weng.quick_blog.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 翁丞健
 * @Date 2021/12/23 20:03
 * @Version 1.0.0
 */
@Data
public class QueryTagListRequest extends PageRequest implements Serializable {
    private static final Long serialVersionUID =1L;

    private String name;

    private Integer type;
}
