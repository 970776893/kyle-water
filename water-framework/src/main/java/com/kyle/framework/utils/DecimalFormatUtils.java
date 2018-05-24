package com.kyle.framework.utils;

import java.text.DecimalFormat;

/**
 * 数字格式化
 * Created by zhangkai on 3/31/16.
 */
public class DecimalFormatUtils {
    private static DecimalFormat df = new DecimalFormat();
    public static String format(Long number , String pattern){
        df.applyPattern(pattern);
        return df.format(number);
    }
}
