package com.ingta.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 格式化时间工具类
 */
public class DateUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String MONTH_FORMAT = "yyyy-MM";
    public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_CH_FORMAT = "yyyy年MM月dd日";
    public final static String YEAR_FORMAT = "yyyy";
    public final static String MONTH_FORMAT_2 = "MM";

    /**
     * 按格式解析日期格式的字符串
     */
    public static Date parse(String val, String format) {
        if (val == null || val.length() == 0) {
            return null;
        }
        DateFormat formart = new SimpleDateFormat(format);
        try {
            return formart.parse(val);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化日期类型为字符串yyy-MM-dd
     */
    public static String formatDate(Long date) {
        return format(new Date(date), DATE_FORMAT);
    }

    /**
     * 格式化日期类型为字符串yyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * 格式化日期类型为字符串yyy年MM月dd日
     */
    public static String formatDateCh(Date date) {
        return format(date, DATE_CH_FORMAT);
    }

    /**
     * 格式化时间类型为字符串yyyy-MM-dd HH:mm:ss
     */
    public static String formatTime(Long time) {
        return format(new Date(time), TIME_FORMAT);
    }

    /**
     * 格式化时间类型为字符串yyyy-MM-dd HH:mm:ss
     */
    public static String formatTime(Date time) {
        return format(time, TIME_FORMAT);
    }

    /**
     * 格式化时间类型为月份yyyy-MM
     */
    public static String formatMonth(Long month) {
        return format(new Date(month), MONTH_FORMAT);
    }

    /**
     * 格式化时间类型为年份yyyy
     */
    public static String getYear(Date date) {
        return format(date, YEAR_FORMAT);
    }

    /**
     * 格式化时间类型为月份
     */
    public static String getMonth(Date date) {
        return format(date, MONTH_FORMAT_2);
    }

    /**
     * 格式化时间类型为月份yyyy-MM
     */
    public static String formatMonth(Date month) {
        return format(month, MONTH_FORMAT);
    }

    /**
     * 返回当前日期yyyy-MM-dd
     */
    public static String formatNowDate() {
        return format(new Date(), DATE_FORMAT);
    }

    /**
     * 返回当前月份yyyy-MM
     */
    public static String formatNowMonth() {
        return format(new Date(), MONTH_FORMAT);
    }

    /**
     * 返回当前时间yyyy-MM-dd HH:mm:ss
     */
    public static String formatNowTime() {
        return format(new Date(), TIME_FORMAT);
    }

    /**
     * 按照相应的格式格式化时间类型为字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat formart = new SimpleDateFormat(format);
        return formart.format(date);
    }

    /**
     * 以当前月份（month）返回num个月前的月份
     */
    public static String aheadMonth(int num, String month) {
        Date _month = parse(month, MONTH_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(_month);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - num);
        return format(cal.getTime(), MONTH_FORMAT);
    }

    /**
     * 以当前月份（month）返回num个月前的月份
     */
    public static Date lastMonth(int num, Date month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + num);
        return cal.getTime();
    }

    /**
     * 以当前日期（date）返回num个月前的日期
     */
    public static String aheadMonthDate(int num, String date) {
        Date _date = parse(date, DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(_date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - num);
        return format(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 以当前日期（date）返回持续num年后的结束日期
     */
    public static Date afterYearDate(int num, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + num);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(afterYearDate(3, new Date()));
    }
}
