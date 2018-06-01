package com.kyle.water.activity.service.impl;


import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.service.impl.BaseServiceImpl;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.entity.ActivityDetail;
import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.water.activity.service.IActivityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 *  Created by kyle.
 *
 */
@Service
@Log4j2
public class ActivityServiceImpl extends BaseServiceImpl<IActivityDao, ActivityEntity> implements IActivityService {

    @Override
    public ActivityDetail getDetailByCode(String code) {
        log.info("根据活动编码获取详情 code:{}", code);
        if(StringUtils.isEmpty(code)) {
            throw new KyleExceptioin("活动编码为空");
        }
        ActivityDetail deail = dao.getDetailByCode(code);
        return deail;
    }

    @Override
    @Transactional
    public String create(ActivityDetail activity) {
        return null;
    }
}
