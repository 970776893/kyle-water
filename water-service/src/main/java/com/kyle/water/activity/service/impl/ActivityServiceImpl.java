package com.kyle.water.activity.service.impl;


import com.alibaba.fastjson.JSON;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.service.impl.BaseServiceImpl;
import com.kyle.framework.utils.Valids;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.entity.ActivityDetail;
import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.water.activity.service.IActivityService;
import com.kyle.water.activityEnrollField.entity.ActivityEnrollFieldEntity;
import com.kyle.water.activityEnrollField.service.IActivityEnrollFieldService;
import com.kyle.water.activityEnrollField.service.impl.ActivityEnrollFieldServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by kyle.
 */
@Service
@Log4j2
public class ActivityServiceImpl extends BaseServiceImpl<IActivityDao, ActivityEntity> implements IActivityService {

    @Autowired
    private IActivityEnrollFieldService activityEnrollFieldService;

    @Override
    public ActivityDetail getDetailByCode(String code) {
        log.info("根据活动编码获取详情 code:{}", code);
        if (StringUtils.isEmpty(code)) {
            throw new KyleExceptioin("活动编码为空");
        }
        ActivityDetail deail = dao.getDetailByCode(code);
        return deail;
    }

    @Override
    @Transactional
    public String create(ActivityDetail activity) {
        valid4Create(activity);
        init4Create(activity);
        insert(activity);
        activityEnrollFieldService.batchInsert(activity.getFields());
        log.info(JSON.toJSON(activity));
        return activity.getCode();
    }


    /**
     * 校验参数是否合法
     *
     * @param activity
     */
    private void valid4Create(ActivityDetail activity) {
        Valids.requireNonNull(activity, "参数不合法");
        Valids.requireNonEmpty(activity.getFields(), "参数不合法");
        Valids.requireNonEmpty(activity.getName(), "参数不合法");
        Valids.requireNonEmpty(activity.getTitle(), "参数不合法");
        Valids.requireNonEmpty(activity.getCode(), "参数不合法");
        Valids.requireNonNull(activity.getType(), "参数不合法");

        ActivityDetail existActivity = dao.getDetailByCode(activity.getCode());
        Valids.requireNull(existActivity, "活动编码已存在");

        Valids.requireGreater(activity.getEndTime(), activity.getStartTime(), "开始结束时间错误");
    }

    private void init4Create(ActivityDetail activity) {
        for (ActivityEnrollFieldEntity fieldEntity : activity.getFields()) {
            fieldEntity.setActivityCode(activity.getCode());
        }
    }
}
