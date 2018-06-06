package com.kyle.water.enums;


/**
 * @author -- kyle
 *         6/6/18 00:09
 */
public enum UserTypeEnums {

    SUPPER_ADMIN(0, "超级管理员"),
    ADMIN(1, "普通管理员"),
    ;

    public int value;
    public String desc;

    UserTypeEnums(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserTypeEnums getByKey(String key) {
        for (UserTypeEnums $s : UserTypeEnums.values()) {
            if ($s.equals(key)) {
                return $s;
            }
        }
        return null;
    }
}
