package com.kyle.framework.utils;

import java.util.Random;

/**
 * Created by zhangkai on 3/24/16.
 */
public class RandomUtils {
    private static Random random = new Random();
    /**
     * 获取指定长度(1-9)的随机数
     * @param length 随机数的长度
     * @return 返回指定长度的随机数，-1表示失败
     */
    public static int random(int length){
        if(length <= 0 || length > 9){
            return -1;
        }
        int maxNumber = (int) Math.pow(10,length);
        int subMax = (int) Math.pow(10,length - 1);
        int result = random.nextInt(maxNumber - subMax);
        return result + subMax - 1;
    }
}
