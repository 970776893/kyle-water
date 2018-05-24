package com.kyle.framework.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 键值对
 * Created by zhangkai on 3/30/16.
 */
@Data
public class KeyValue<T, E> implements Serializable{
    private T key;
    private E value;

    public KeyValue(T key, E value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }
}
