package com.kyle.water.user.controller;

import com.kyle.framework.annotation.SystemLog;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.LoginUserInfo;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.utils.Constants;
import com.kyle.water.user.entity.UserEntity;
import com.kyle.water.user.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author -- kyle
 *         6/6/18 01:51
 */
@Controller
@RequestMapping("/")
@Log4j2
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @SystemLog(description = "登录")
    public ModelResult<String> login(@RequestParam String loginCode, @RequestParam String password) {
        ModelResult<String> result = new ModelResult<>("请求成功");
        try {
            UserEntity userEntity = userService.getByLoginCodeCodeAndPassword(loginCode, password);
            if (userEntity == null) {
                throw new KyleExceptioin("用户名或密码错误");
            }
            LoginUserInfo userInfo = new LoginUserInfo();
            userInfo.setUserCode(userEntity.getUserCode());
            userInfo.setUserName(userEntity.getUserName());
            request.getSession().setAttribute(Constants.LOGIN_SESSION_ATTRIBUTE_KEY, userInfo);
            result.setData("登录成功");
        } catch (KyleExceptioin k) {
            result.failure("用户名或密码错误");
            log.error("用户名或密码错误", k);
        } catch (Throwable e) {
            result.failure("查询异常");
            log.error("查询异常", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @SystemLog(description = "退出登录")
    public ModelResult<String> logout() {
        ModelResult<String> result = new ModelResult<>("退出登录成功");
        try {
            HttpSession sessioin = request.getSession();
            Enumeration attributeEration = sessioin.getAttributeNames();
            while (attributeEration.hasMoreElements()) {
                String attributeName = (String) attributeEration.nextElement();
                sessioin.removeAttribute(attributeName);
            }
        } catch (Throwable e) {
            result.failure("退出登录异常");
            log.error("退出登录异常", e);
        }
        return result;
    }
}
