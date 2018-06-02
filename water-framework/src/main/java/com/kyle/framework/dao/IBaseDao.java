package com.kyle.framework.dao;


import com.kyle.framework.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-2-23.
 */
public interface IBaseDao<E extends BaseEntity> {

    void insert(E item);

    void insertSelective(E item);

    void batchInsert(List<E> list);

    void updateSelectiveById(E item);

    Integer countByParams(Map<String, Object> param);

    List<E> listByParams(Map<String, Object> param);

    E selectById(Long id);

    void deleteById(Long id);
}
