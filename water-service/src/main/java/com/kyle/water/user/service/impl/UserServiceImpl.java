package com.kyle.water.user.service.impl;


import com.kyle.framework.service.impl.BaseServiceImpl;
import com.kyle.framework.utils.PatternMatchUtils;
import com.kyle.framework.utils.Valids;
import com.kyle.water.user.dao.IUserDao;
import com.kyle.water.user.entity.UserEntity;
import com.kyle.water.user.service.IUserService;
import com.kyle.framework.utils.UserInfoUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Created by kyle.
 */
@Service
@Log4j2
public class UserServiceImpl extends BaseServiceImpl<IUserDao, UserEntity> implements IUserService {

    @Override
    public void create(UserEntity userEntity) {
        valid4Create(userEntity);
        init4Create(userEntity);
        insert(userEntity);
    }

    @Override
    public UserEntity getByUserCode(String userCode) {
        Valids.requireNonEmpty(userCode, "用户编码为空");
        return getSingleByField("userCode", userCode);
    }

    @Override
    public UserEntity getByLoginCodeCodeAndPassword(String loginCode, String password) {
        Valids.requireNonEmpty(loginCode, "登录名为空");
        Valids.requireNonEmpty(password, "密码为空");
        UserEntity user = getByLogincode(loginCode);
        if (user == null) {
            log.warn("登录失败：loginCode:{}", loginCode);
            return null;
        }
        String encryPassword = UserInfoUtils.encryPassword(password, user.getMobile());
        boolean isMatch = encryPassword.equals(user.getPassword());
        return isMatch ? user : null;
    }

    private UserEntity getByLogincode(String loginCode) {
        UserEntity user = getByUserCode(loginCode);
        if (user == null) {
            user = getSingleByField("mobile", loginCode);
        }
        return user;
    }

    private void valid4Create(UserEntity userEntity) {
        Valids.requireNonNull(userEntity, "参数不合法");
        Valids.requireTrue(PatternMatchUtils.matchPassword(userEntity.getPassword()), "密码不合法:6-21字母和数字组成，不能是纯数字或纯英文");
        Valids.requireTrue(PatternMatchUtils.matchMobile(userEntity.getMobile()), "手机号不合法");
        Valids.requireTrue(PatternMatchUtils.matchUserCode(userEntity.getUserCode()), "登录登录账号不合法:长度为6~20，首位为字母，包含字母、数字、@、.、_");
        Valids.requireTrue(PatternMatchUtils.matchUserName(userEntity.getUserName()), "姓名合法：长度为2~20");
        Valids.requireTrue(PatternMatchUtils.matchMobile(userEntity.getMobile()), "手机号不合法");
        //用户名不重复
        UserEntity user = getByUserCode(userEntity.getUserCode());
        Valids.requireNull(user, "用户编码已存在");
        //手机号不重复
        user = getSingleByField("mobile", user.getMobile());
        Valids.requireNull(user, "手机号已存在");
    }

    private void init4Create(UserEntity userEntity) {
        String encryPasword = UserInfoUtils.encryPassword(userEntity.getPassword(), userEntity.getMobile());
        userEntity.setPassword(encryPasword);
    }


}
