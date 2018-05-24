package com.kyle.framework.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangkai on 16-2-23.
 */
@Data
public class ModelPage<T> implements Serializable{
    private Integer pageNo;
    private Integer pageSize;
    private Integer total;
    private List<T> itemList;
}
