package com.kyle.water.activity.controller;

import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.service.IActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  Created by kyle.
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends IBaseControllor<ActivityEntity, IActivityDao, IActivityService> {


}
