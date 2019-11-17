package com.exercise.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/15 23:09
 */
public class StringUtil {

    private static final Pattern patternInteger = Pattern.compile("^[-\\+]?[\\d]*$");

    public static String TrimEnd(String strValue, String trimStr) {
        if (StringUtils.isBlank(strValue) || StringUtils.isBlank(trimStr)) {
            return strValue;
        }
        boolean endWith = StringUtils.endsWith(strValue, trimStr);
        if (endWith) {
            return StringUtils.substring(strValue, 0, strValue.length() - trimStr.length());
        } else {
            return strValue;
        }
    }

    /**
     * 判断是否为整数(推荐，速度最快)
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        return patternInteger.matcher(str).matches();
    }
}
