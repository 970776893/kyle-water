package com.kyle.water.user.service;

import com.kyle.water.user.entity.UserEntity;
import com.kyle.water.user.dao.IUserDao;
import com.kyle.framework.service.IBaseService;

/**
 *
 *  Created by kyle.
 *
 */
public interface IUserService  extends IBaseService< IUserDao, UserEntity>{

    /**
     * 创建用户
     * @param userEntity
     */
    void create(UserEntity userEntity);

    /**
     * 根据用户编码获取用户手机号
     * @param userCode
     * @return
     */
    UserEntity getByUserCode(String userCode);


    /**
     * 校验登录名和面膜密码是否匹配
     * @param loginCode 登录名：用户编码/用户手机号
     * @param password 明文密码
     * @return 返回匹配的用户信息
     */
    UserEntity matchUserCodeAndPassword(String loginCode, String password);

}
