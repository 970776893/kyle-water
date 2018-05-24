package com.kyle.framework.service;

import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.model.ModelPage;
import com.kyle.framework.model.ModelResult;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-2-23.
 */
public interface IBaseService<T extends IBaseDao<E>, E extends BaseEntity> {

    E get(Long id);

    ModelResult<String> delete(Long id);

    ModelResult<E> create(E item);

    ModelResult<String> modifyById(E item);

    ModelPage<E> getPage(Map<String, Object> param);

    Integer getCount(Map<String, Object> param);

    List<E> getPageList(Map<String, Object> param);

    List<E> getList(Map<String, Object> param);

}
