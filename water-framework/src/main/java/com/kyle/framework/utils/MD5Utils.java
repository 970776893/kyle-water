package com.kyle.framework.utils;

import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangkai on 3/30/16.
 */
@Log4j2
public class MD5Utils {

    private static char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 对字符串进行 MD5 签名
     *
     * @param str 待签名字符串
     * @return 加密后字符串
     *
     */
    public static String md5Sum(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
            throw new RuntimeException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = Digit[encoded >> 4 & 0xf];
            finalValue[k++] = Digit[encoded & 0xf];
        }
        return new String(finalValue);
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名
     * @return 签名结果
     */
    public static boolean verify(String text, String sign) {
        String mysign = md5Sum(text);
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }
}
