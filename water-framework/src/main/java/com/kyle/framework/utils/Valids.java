package com.kyle.framework.utils;

import com.kyle.framework.exception.KyleExceptioin;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author -- zhangkai02
 *         6/2/18 21:27
 */
public class Valids {

    public static void requireNonNull(Object obj, String message){
        if(obj == null) {
            throw new KyleExceptioin(message);
        }
    }

    public static void requireNull(Object obj, String message){
        if(obj != null) {
            throw new KyleExceptioin(message);
        }
    }

    public static void requireNonEmpty(String obj, String message){
        if(StringUtils.isEmpty(obj)) {
            throw new KyleExceptioin(message);
        }
    }

    public static void requireNonEmpty(Collection obj, String message){
        if(CollectionUtils.isEmpty(obj)) {
            throw new KyleExceptioin(message);
        }
    }

    public static void requireGreater(Long o1, Long o2, String message){
        if(o1 <= o2) {
            throw new KyleExceptioin(message);
        }
    }
}
