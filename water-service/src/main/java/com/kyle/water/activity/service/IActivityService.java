package com.kyle.water.activity.service;

import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.service.IBaseService;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.entity.ActivityDetail;
import com.kyle.water.activity.entity.ActivityEntity;

/**
 *
 *  Created by kyle.
 *
 */
public interface IActivityService  extends IBaseService< IActivityDao, ActivityEntity>{


    /**
     * 获取一个活动的详细信息
     * @param code
     * @return
     */
    ActivityDetail getDetailByCode(String code) throws KyleExceptioin;
}
