package com.exercise.demo.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/11/19 20:42
 */
public class LocalDateTimeUtil {


    public static final DateTimeFormatter DF_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DF_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DF_HH_MM_SS = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter DF_HH_MM = DateTimeFormatter.ofPattern("HH:mm");

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),

    //Date转换为LocalDateTime
    public static LocalDateTime convertDateToLDT(Date date) {
       //  LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return  date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    //LocalDateTime转换为Date
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    //获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    //获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    //获取指定时间的指定格式
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    //获取当前时间的指定格式
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    //日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    //日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    public static void main(String[] args) {

        LocalDateTime now=convertDateToLDT(new Date());
        System.out.println(ZoneId.systemDefault());

        System.out.println("Current now: " + now);

        String timeee="2019-11-19 22:11:22";
        System.out.println(LocalDateTime.parse(timeee,DF_YYYY_MM_DD_HH_MM_SS).format(DF_YYYY_MM_DD));
        System.out.println(formatNow("yyyy年MM月dd日 HH:mm:ss"));
        LocalDateTime start = LocalDateTime.of(1993, 10, 13, 11, 11);
        LocalDateTime end = LocalDateTime.of(1994, 11, 13, 13, 13);
        System.out.println("年:" + betweenTwoTime(start, end, ChronoUnit.YEARS));
        System.out.println("月:" + betweenTwoTime(start, end, ChronoUnit.MONTHS));
        System.out.println("日:" + betweenTwoTime(start, end, ChronoUnit.DAYS));
        System.out.println("半日:" + betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
        System.out.println("小时:" + betweenTwoTime(start, end, ChronoUnit.HOURS));
        System.out.println("分钟:" + betweenTwoTime(start, end, ChronoUnit.MINUTES));
        System.out.println("秒:" + betweenTwoTime(start, end, ChronoUnit.SECONDS));
        System.out.println("毫秒:" + betweenTwoTime(start, end, ChronoUnit.MILLIS));
        //增加二十分钟
        System.out.println(formatTime(plus(LocalDateTime.now(), 20, ChronoUnit.MINUTES), "yyyy年MM月dd日 HH:mm"));
        //增加两年
        System.out.println(formatTime(plus(LocalDateTime.now(), 2, ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm"));
        System.out.println(getDayStart(LocalDateTime.now()));
        System.out.println(getDayEnd(LocalDateTime.now()));

        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);
        //add 1 week to the current date
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);
        //add 1 month to the current date
        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);
        //add 1 year to the current date
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);
        //add 10 years to the current date
        LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);

        LocalTime todayTime = LocalTime.now();
        System.out.println("Current LocalTime: " + todayTime);

    }

}
