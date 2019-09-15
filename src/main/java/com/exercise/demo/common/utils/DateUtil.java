package com.exercise.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/9 14:29
 */
public class DateUtil {

    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");//"GMT+08:00"

    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = 60 * 1000;
    public static final long MILLIS_PER_HOUR = 60 * 60 * 1000;
    public static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = 60 * 60;
    public static final int SECONDS_PER_DAY = 24 * 60 * 60;

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd_00_00_00 = "yyyy-MM-dd 00:00:00";
    public static final String yyyy_MM_dd_HH_mm_CH = "yyyy年MM月dd日 HH:mm";
    public static final String yyyy_MM_dd_CH = "yyyy年MM月dd日";

    public static String format_yyyy_MM_dd_HH_mm_ss(Date date) {
        return format(yyyy_MM_dd_HH_mm_ss, date);
    }

    public static String format_yyyy_MM_dd(Date date) {
        return format(yyyy_MM_dd, date);
    }

    public static String format_yyyyMMdd(Date date) {
        return format(yyyyMMdd, date);
    }

    public static String format_yyyy_MM_dd_00_00_00(Date date) {
        return format(yyyy_MM_dd_00_00_00, date);
    }

    public static String format_yyyy_MM_dd_HH_mm_CH(Date date) {
        return format(yyyy_MM_dd_HH_mm_CH, date);
    }

    public static String format_yyyy_MM_dd_CH(Date date) {
        return format(yyyy_MM_dd_CH, date);
    }

    public static Date parse_yyyy_MM_dd_HH_mm_ss(String timeStr) {
        return parse(yyyy_MM_dd_HH_mm_ss, timeStr);
    }

    public static Date parse_yyyy_MM_dd(String timeStr) {
        return parse(yyyy_MM_dd, timeStr);
    }

    public static Date parse(String format, String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String format(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }


    public static boolean isAdult(Date birthday) {
        Calendar calendar = Calendar.getInstance(UTC_TIME_ZONE);
        calendar.add(Calendar.YEAR, -18);
        return calendar.getTime().after(birthday);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDate 较小的时间
     * @param bigDate   较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smallDate, Date bigDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
        smallDate = sdf.parse(sdf.format(smallDate));
        bigDate = sdf.parse(sdf.format(bigDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smallDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bigDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / MILLIS_PER_DAY;
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 通过时间秒毫秒数判断两个时间间隔天数（满24小时进1天）
     *
     * @param date1 被减时间
     * @param date2
     * @return
     */
    public static int daysBetweenByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / MILLIS_PER_DAY);
    }
}
