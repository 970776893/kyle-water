package com.kyle.framework.model;

/**
 * Created by zhangkai on 16-1-29.
 */
public enum ReturnCodeEnum {

    /**
     * 请求成功
     */
    REQUEST_SUCESS(200, "success"),
    /**
     * 请求失败
     */
    REQUEST_FAILED(500, "success"),
    /**
     * 未登录
     */
    NO_LOGIN(10001, "未登录授权");

    public String desc;
    public int code;

    ReturnCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
