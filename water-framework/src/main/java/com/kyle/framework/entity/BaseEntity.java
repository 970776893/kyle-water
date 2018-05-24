package com.kyle.framework.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础实体类
 * Created by zhangkai on 16-2-19.
 */
@Data
public class BaseEntity{
    /**
     * DB主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Long createAt;
    /**
     * 创建人ID
     */
    private String createBy;
    /**
     * 创建人姓名
     */
    private String createName;
    /**
     * 最后修改时间
     */
    private Long updateAt;
    /**
     * 最后修改人ID
     */
    private String updateBy;
    /**
     * 最后修改人姓名
     */
    private String updateName;
    /**
     * 是否删除
     */
    private Boolean isDel;
}
