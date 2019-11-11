package com.lw.common.utils;



import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lw.common.constant.ErrorCode;
import com.lw.common.exception.BaseException;


public class Validate {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_PHONE_NUMBER = "^(1[0-9])\\d{9}$";

    /**
     * 正则表达式：用户姓名，只允许包含字母与中文，通配符匹配所有非字母与中文字符
     */
    public static final String REGEX_USER_NAME = "^[a-zA-Z\u4E00-\u9FA5]+$";

    public static void isNotNull(Object object) {
        if (object == null) {
            throw new BaseException(ErrorCode.NULL_PARAM, null);
        }
    }

    public static void isNotNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new BaseException(errorCode, null);
        }
    }

    public static void isNotNull(Object object, ErrorCode errorCode, String message) {
        if (object == null) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void isNotEmpty(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseException(ErrorCode.NULL_PARAM);
        }
    }
    public static void isNotEmpty(String str, ErrorCode errorCode, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void isNotEmpty(String str, ErrorCode errorCode) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseException(errorCode);
        }
    }

    public static void notEmpty(Collection<?> object, ErrorCode errorCode, String message) {
        if (object == null || object.size() == 0) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void notEmpty(Collection<?> object, ErrorCode errorCode) {
        if (object == null || object.size() == 0) {
            throw new BaseException(errorCode);
        }
    }

    public static void notEmpty(Object[] object, ErrorCode errorCode, String message) {
        if (object == null || object.length == 0) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void notEmpty(Object[] object, ErrorCode errorCode) {
        if (object == null || object.length == 0) {
            throw new BaseException(errorCode);
        }
    }

    public static void containsNoNulls(Iterable<?> collection, ErrorCode errorCode, String message) {
        Iterator var2 = collection.iterator();

        while (var2.hasNext()) {
            Object object = var2.next();
            isNotNull(object, errorCode, message);
        }
    }

    public static void containsNoNulls(Object[] array, ErrorCode errorCode, String message) {
        Object[] var2 = array;
        int var3 = array.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Object object = var2[var4];
            isNotNull(object, errorCode, message);
        }

    }

    public static void isTrue(boolean condition, ErrorCode errorCode, String message) {
        if (!condition) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void isFalse(boolean condition, ErrorCode errorCode, String message) {
        if (condition) {
            throw new BaseException(errorCode, message);
        }
    }

    public static void isPhoneNumber(String number) {
        if (!Pattern.matches(REGEX_PHONE_NUMBER, number)) {
            throw new BaseException(ErrorCode.INVALID_PHONE_NUMBER);
        }
    }

    public static void isValidName(String content) {
        Pattern pattern = Pattern.compile(REGEX_USER_NAME);
        Matcher match = pattern.matcher(content);

        if (!match.matches()) {
            throw new BaseException(ErrorCode.INVALID_PHONE_NUMBER);
        }
    }

}
