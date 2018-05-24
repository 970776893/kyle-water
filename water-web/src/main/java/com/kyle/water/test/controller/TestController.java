package com.kyle.water.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.water.test.dao.ITestDao;
import com.kyle.water.test.entity.TestEntity;
import com.kyle.water.test.service.ITestService;

/**
 *
 *  Created by kyle.
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends IBaseControllor<TestEntity, ITestDao, ITestService> {

}
