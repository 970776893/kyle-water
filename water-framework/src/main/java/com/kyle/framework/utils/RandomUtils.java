package com.kyle.framework.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by zhangkai on 3/24/16.
 */
public class RandomUtils {
    private static Random RANDOM = new SecureRandom();

    private static String RANDOM_CANDIDATE_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyz";

    private static Integer NUMBERS_TOTAL_COUNT = 10;

    private static Integer LETTERS_TOTAL_COUNT = 26;

    /**
     * 从{RANDOM_CANDIDATE_LETTERS}获取随机字符
     * @param offset 开始位置下标（包含）
     * @param size 候选字符的长度
     * @return
     */
    private static char next(int offset, int size) {
        int index = offset + RANDOM.nextInt(size);
        return RANDOM_CANDIDATE_LETTERS.charAt(index);
    }

    /**
     * 从{RANDOM_CANDIDATE_LETTERS}获取随机字符
     * @param size 候选字符的长度 也就是0~size中选取
     * @return
     */
    private static char next(int size) {
        return next(0, size);
    }

    /**
     * 获取指定长度的纯数字型随机数
     * @param length 随机数的长度
     * @return 随机数
     */
    public static String randomNumber(int length){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < length; i++){
            char rand = next(NUMBERS_TOTAL_COUNT);
            result.append(rand);
        }
        return result.toString();
    }

    /**
     * 获取指定长度的纯字母型随机数
     * @param length 随机数的长度
     * @return 随机数
     */
    public static String randomLetter(int length){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < length; i++){
            char rand = next(NUMBERS_TOTAL_COUNT, NUMBERS_TOTAL_COUNT);
            result.append(rand);
        }
        return result.toString();
    }


    /**
     * 获取指定长度的字母+数字型随机数
     * @param length 随机数的长度
     * @return 随机数
     */
    public static String random(int length){
        StringBuilder result = new StringBuilder();
        int randomBound = LETTERS_TOTAL_COUNT + NUMBERS_TOTAL_COUNT;
        for(int i = 0; i < length; i++){
            char rand = next(randomBound);
            result.append(rand);
        }
        return result.toString();
    }
}
