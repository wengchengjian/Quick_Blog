/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version moduleEnum:ModuleEnum.java v1.0 2021/12/2 5:33 下午 wengchengjian Exp $
 */
@Getter
public enum ModuleEnum {
    ARTICLE(0),

    BOOK(1),

    BOOK_NOTE(2);

    private int value;

    ModuleEnum(int value){this.value = value;}
}
