package com.kyle.water.activity.entity;

import com.kyle.framework.entity.BaseEntity;
import lombok.Data;
import sun.jvm.hotspot.types.basic.BasicOopField;

/**
 *
 *  Created by kyle.
 *
 */
@Data
public class ActivityEntity  extends BaseEntity {

    /**
    *
    * 活动编码
    */
    private String code;
    /**
    *
    * 活动名称
    */
    private String name;
    /**
    *
    * 活动标题
    */
    private String title;
    /**
    *
    * 开始时间
    */
    private Long startTime;
    /**
    *
    * 结束时间
    */
    private Long endTime;
    /**
    *
    * 状态（0-活动未开始 1-活动已上架 2-活动已下架）
    */
    private Integer status;
    /**
    *
    * 是否发送结果的短信通知（0-发送短息 1-不发送短信）
    */
    private Boolean sendResultMsg = false;
    /**
    *
    * 类型（0-报名活动，1-投票活动，2-问卷答题，3-抽奖活动，4-抢购活动）
    */
    private Integer type;

}
