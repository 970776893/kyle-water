package com.kyle.framework.model;

import lombok.Data;

import java.io.Serializable;

/**
 * session中的用户信息
 *
 * @author -- kyle
 *         6/5/18 23:37
 */
@Data
public class LoginUserInfo implements Serializable {
    private String userCode;
    private String userName;
}
