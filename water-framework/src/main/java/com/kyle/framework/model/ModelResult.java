package com.kyle.framework.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhangkai on 16-1-19.
 * 封装结果汇总
 */
@Data
public class ModelResult<T> implements Serializable{
    private int code = 200;
    private String message;

    private Object extaData;

    private T data;

    public ModelResult(){
    }
    public ModelResult(String message){
        this.message= message;
    }
}
