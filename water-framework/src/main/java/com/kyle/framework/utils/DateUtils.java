package com.kyle.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 16-2-27.
 */
public class DateUtils {
    public static SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 按照指定的格式，时间格式化（默认格式 yyyy-MM-dd HH:mm:ss）
     * @param time
     * @return
     */
    public static String format(Long time){
        return defaultFormat.format(time);
    }
    /**
     * 按照指定的格式，时间格式化
     * @param time
     * @param fromat
     * @return
     */
    public static String format(Long time, String fromat){
        SimpleDateFormat format = new SimpleDateFormat(fromat);
        return format.format(time);
    }


    /**
     * 按照指定的格式，时间格式化（默认格式 yyyy-MM-dd HH:mm:ss）
     * @param time
     * @return
     */
    public static String format(Date time){
        return defaultFormat.format(time);
    }

    /**
     * 按照指定的格式，时间格式化
     * @param time
     * @param fromat
     * @return
     */
    public static String format(Date time, String fromat){
        SimpleDateFormat format = new SimpleDateFormat(fromat);
        return format.format(time);
    }

    /**
     * 按照默认的格式，解析时间（默认格式 yyyy-MM-dd HH:mm:ss）
     * @param time
     * @return
     */
    public static Date parse(String time) throws ParseException {
        return defaultFormat.parse(time);
    }

    /**
     * 按照指定的格式，解析时间
     * @param time
     * @param fromat
     * @return
     */
    public static Date parse(String time, String fromat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(fromat);
        return format.parse(time);
    }
}
