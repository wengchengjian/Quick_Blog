/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  日期工具类
 * </p>
 * @author wengchengjian
 * @version dateUtils:DateUtils.java v1.0 2021/12/1 3:47 下午 wengchengjian Exp $
 */
public class DateUtils {
    private static final ThreadLocal<SimpleDateFormat> formatters = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat();
    });
    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        SimpleDateFormat formatter =formatters.get();
        formatter.applyPattern(pattern);
        return formatter.format(date);
    }
}
