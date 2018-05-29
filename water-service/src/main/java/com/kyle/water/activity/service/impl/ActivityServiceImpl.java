package com.kyle.water.activity.service.impl;


import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.service.IActivityService;
import com.kyle.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 *  Created by kyle.
 *
 */
@Service
public class ActivityServiceImpl extends BaseServiceImpl<IActivityDao, ActivityEntity> implements IActivityService {

}
