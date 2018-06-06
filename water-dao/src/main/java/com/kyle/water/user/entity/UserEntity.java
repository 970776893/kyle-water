package com.kyle.water.user.entity;

import com.kyle.framework.entity.BaseEntity;
import lombok.Data;
/**
 *
 *  Created by kyle.
 *
 */
@Data
public class UserEntity  extends BaseEntity {

    /**
    *
    * 登录账号
    */
    private String userCode;
    /**
    *
    * 登录密码
    */
    transient private String password;
    /**
    *
    * 用户名成
    */
    private String userName;
    /**
    *
    * 用户手机号
    */
    private String mobile;
    /**
    *
    * 用户类型(0-超级管理员 1-普通管理员)
    */
    private Integer type;

}
