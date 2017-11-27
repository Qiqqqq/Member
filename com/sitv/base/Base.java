package com.sitv.base;

import java.util.List;
import java.util.Map;

public abstract class Base {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String SEPARATOR = "/";
    public static final String BLANK = "";
    public static final String BLANK_SPACE = " ";
    public static final String COMMA_DELIMITED = ",";


    public static boolean notEmpty(String var) {
        return null != var && "" != var;
    }

    public static boolean empty(String var) {
        return null == var && "" == var;
    }

    public static boolean notEmpty(Object var) {
        return null != var;
    }

    public static boolean notEmpty(List<?> var) {
        return null != var && !var.isEmpty();
    }

    public static boolean notEmpty(Map<?, ?> var) {
        return null != var && !var.isEmpty();
    }

    public static boolean empty(List<?> var) {
        return null == var || var.isEmpty();
    }

    public static boolean empty(Map<?, ?> var) {
        return null == var || var.isEmpty();
    }

    public static boolean empty(Object var) {
        return null == var;
    }

    public static boolean empty(Object[] var) {
        return null == var || 0 == var.length;
    }

    public static boolean notEmpty(Object[] var) {
        return null != var && 0 < var.length;
    }
}
