package com.kyle.water.user.controller;

import com.kyle.framework.annotation.SystemLog;
import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.ModelResult;
import com.kyle.water.user.entity.UserEntity;
import com.kyle.water.user.dao.IUserDao;
import com.kyle.water.user.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kyle.
 */
@Controller
@RequestMapping("/user")
@Log4j2
public class UserController extends IBaseControllor<UserEntity, IUserDao, IUserService> {

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @SystemLog(description = "新增内容")
    public ModelResult<String> create(@RequestBody UserEntity item) {
        ModelResult<String> result = new ModelResult<>("新增成功");
        try {
            service.create(item);
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("新增失败", k);
        } catch (Throwable e) {
            result.failure("新增异常");
            log.error("新增异常", e);
        }
        return result;
    }
}
