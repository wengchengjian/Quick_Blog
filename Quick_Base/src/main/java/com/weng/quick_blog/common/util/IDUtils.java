package com.weng.quick_blog.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 20:24
 * @Version 1.0.0
 */
public class IDUtils {

    private static  IDType type = IDType.UUID;


    public static final Object generate(){

        Object res = null;
        switch(type){
            case UUID:
                res = generateUUID();
                break;
            case SNOWFLAKE:
                res = generateSnowflake();
                break;

        }
        return res;
    }

    public static final void setType(IDType type){
        type = type;
    }

    public static final IDType getType(){
        return type;
    }


    public static final String generateUUID(){
        return UUID.randomUUID().toString() + System.currentTimeMillis();
    }

    /**
     * TODO snowflake算法未实现
     * @return
     */
    public static final String generateSnowflake(){
        return UUID.randomUUID().toString() + System.currentTimeMillis();
    }

    @Getter
    @AllArgsConstructor
    private enum IDType {
        UUID(0),
        SNOWFLAKE(1);
        Integer type;

    }
}
