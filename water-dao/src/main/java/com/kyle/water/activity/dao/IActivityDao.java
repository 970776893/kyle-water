package com.kyle.water.activity.dao;


import com.kyle.water.activity.entity.ActivityDetail;
import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.framework.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * IActivityDao
 *
 * create by kyle
 */
public interface IActivityDao extends IBaseDao<ActivityEntity>{

    /**
     * 获取一个活动的详细信息
     * @param code
     * @return
     */
    ActivityDetail getDetailByCode(@Param(value = "code") String code);
}