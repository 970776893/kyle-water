package com.kyle.water.activityEnrollField.controller;

import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.water.activityEnrollField.entity.ActivityEnrollFieldEntity;
import com.kyle.water.activityEnrollField.dao.IActivityEnrollFieldDao;
import com.kyle.water.activityEnrollField.service.IActivityEnrollFieldService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  Created by kyle.
 *
 */
@Controller
@RequestMapping("/activityEnrollField")
public class ActivityEnrollFieldController extends IBaseControllor<ActivityEnrollFieldEntity, IActivityEnrollFieldDao, IActivityEnrollFieldService> {


}
