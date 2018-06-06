package com.kyle.framework.utils;

import com.kyle.framework.model.LoginUserInfo;

/**
 * @author -- kyle
 *         6/5/18 23:48
 */
public class UserInfoUtils {

    private static ThreadLocal<LoginUserInfo> loginUser = new ThreadLocal<>();

    private static LoginUserInfo systemUser = new LoginUserInfo();

    static {
        systemUser.setUserCode("system");
        systemUser.setUserName("system");
    }

    /**
     * 根据用户信息生产密码的密文
     * 过程为：md5((md5(密码+手机号4~7位)的前30位) + 手机号第8~9)
     *
     * @param password 用户密码
     * @param mobile   用户手机号
     * @return 密码的密文
     */
    public static String encryPassword(String password, String mobile) {
        //密码+手机号4~7位
        String willEncry = password + mobile.substring(3, 7);
        willEncry = MD5Utils.md5Sum(willEncry);

        //md5前30位+手机号第8~9
        willEncry = willEncry.substring(0, 30) + mobile.substring(8, 10);
        willEncry = MD5Utils.md5Sum(willEncry);
        return willEncry;
    }

    /**
     * 获取登录的用户
     * @return 用户信息，如果没有登录返回null
     */
    public static LoginUserInfo getLoginUser() {
        return loginUser.get();
    }


    /**
     * 获取登录的用户
     * @return 用户信息，如果没有登录返回null
     */
    public static LoginUserInfo getLoginUserForce() {
        LoginUserInfo user = getLoginUser();
        if(user == null) {
            user = systemUser;
        }
        return user;
    }

    /**
     * 记录登录用户，（拦截器调用注入，请勿随意调用）
     * @param loginUserInfo
     */
    public static void cacheLoginUser(LoginUserInfo loginUserInfo) {
        loginUser.set(loginUserInfo);
    }
}
