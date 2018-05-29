package com.kyle.water.dictionary.entity;

import com.kyle.framework.entity.BaseEntity;
import lombok.Data;
/**
 *
 *  Created by kyle.
 *
 */
@Data
public class DictionaryEntity  extends BaseEntity {

    /**
    *
    * 字典分组编码
    */
    private String groupCode;
    /**
    *
    * 字典项编码
    */
    private String code;
    /**
    *
    * 字典值
    */
    private String value;

}
