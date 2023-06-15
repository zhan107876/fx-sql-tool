package com.tan.fx.utils;

/**
 * @title: StringUtils
 * @Author Tan
 * @Date: 2022/10/23 0:57
 * @Version 1.0
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}