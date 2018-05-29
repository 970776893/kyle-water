package com.kyle.water.activityEnrollField.entity;

import com.kyle.framework.entity.BaseEntity;
import lombok.Data;
/**
 *
 *  Created by kyle.
 *
 */
@Data
public class ActivityEnrollFieldEntity  extends BaseEntity {

    /**
    *
    * 活动编码
    */
    private String activityCode;
    /**
    *
    * 标签名称
    */
    private String labelName;
    /**
    *
    * 输入项类型(1-text，2-mobile，3-number，4-select，5-date，6-time，7-datetime)
    */
    private Integer filedType;
    /**
    *
    * 针对select的候选项分组
    */
    private String dictionaryGroupCode;
    /**
    *
    * 显示顺序（从0开始越小越靠上）
    */
    private Integer index;
    /**
    *
    * 是否保密(0-不保密，1-保密)
    */
    private Boolean isSecrecy;
    /**
    *
    * 是否作为唯一标识(0-不是，1-是)
    */
    private Boolean isKey;
    /**
    *
    * 是否必输项(0-不是，1-是)
    */
    private Boolean isRequired;
    /**
    *
    * 最小值/长度
    */
    private Integer min;
    /**
    *
    * 最大值/长度
    */
    private Integer max;
    /**
    *
    * 正则表达式限制
    */
    private String pattern;

}
