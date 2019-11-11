package com.lw.common.utils;


import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isEmpty(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        int length = input.length();

        for (int i = 0; i < length; i++) {
            if (!isWhitespace(input.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }
    
    /**
     * 驼峰 转下划线
     * @param camelCase
     * @return
     */
    public static String toLine(String camelCase){
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            //首字母大写不转
            if(matcher.start() == 0) {
                matcher.appendReplacement(sb, matcher.group(0).toLowerCase());
                continue;
            }
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
}
