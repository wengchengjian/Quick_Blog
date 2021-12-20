/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version jsonUtils:JsonUtils.java v1.0 2021/12/1 4:06 下午 wengchengjian Exp $
 */
@Slf4j
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Json字符串转化成对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObj(String jsonStr,Class<T> clazz ){
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        try{
            return objectMapper.readValue(jsonStr,clazz);
        }catch(IOException e){
            log.error("Json字符串转换成对象失败");
        }
        return null;
    }

    public static final String toJson(Object obj){
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double
        || obj instanceof Boolean || obj instanceof String){
            return String.valueOf(obj);
        }
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(JsonProcessingException e){
            log.error("对象转化成Json字符串失败",e);
        }
        return null;
    }
}
