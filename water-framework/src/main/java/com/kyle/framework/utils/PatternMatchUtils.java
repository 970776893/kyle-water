package com.kyle.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author -- kyle
 *         6/6/18 00:30
 */
public class PatternMatchUtils {


    public static String handlerSpecialChars(String text){
        Pattern pattern = Pattern.compile(RegexConstants.SPECIAL_CHARS_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("\\\\$0");
    }

    /**
     * 正则校验
     * @param regex 正则表达式
     * @param text
     * @return
     */
    public static boolean match(String regex, String text){
        if(regex.equals(text)) {
            return true;
        }
        String normalText = handlerSpecialChars(text);
        return Pattern.compile(regex).matcher(normalText).find();
    }

    /**
     * 匹配手机号
     * @param mobile
     * @return
     */
    public static boolean matchMobile(String mobile) {
        String pattern = RegexConstants.MOBIEL_PATTERN;
        return match(pattern, mobile);
    }

    /**
     * 匹配用户
     * @param userName
     * @return
     */
    public static boolean matchUserName(String userName) {
        String pattern = RegexConstants.USERNAME_PATTERN;
        return match(pattern, userName);
    }

    /**
     * 匹配用户登录账号
     * @param userCode
     * @return
     */
    public static boolean matchUserCode(String userCode) {
        String pattern = RegexConstants.USECODE_PATTERN;
        return match(pattern, userCode);
    }

    /**
     * 匹配用户密码
     * @param password
     * @return
     */
    public static boolean matchPassword(String password) {
        String pattern = RegexConstants.PASSWORD_PATTERN;
        return match(pattern, password);
    }


    public static class RegexConstants {

        /**
         * 11个正则表达式的11个字符
         */
        public static String SPECIAL_CHARS_PATTERN = "[\\$\\(\\)\\*\\+\\.\\[\\?\\\\\\^\\{\\}\\|]";

        public static String MOBIEL_PATTERN = "^1[0-9]{10}$";

        public static String USERNAME_PATTERN = "^[\\S]{2,20}$";

        /**
         * 长度为6~20，首位为字母，包含字母、数字、@、.、_
         */
        public static String USECODE_PATTERN = "^[A-Za-z]{1}[0-9A-Za-z_@.]{5,19}$";

        /**
         *
         * (?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}
         * 6-21字母和数字组成，不能是纯数字或纯英文
         *
         */
        //TODO 待完善
        public static String PASSWORD_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";


    }
}
