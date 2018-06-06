package com.kyle.framework.service;

import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.Page;
import com.kyle.framework.model.ModelResult;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-2-23.
 */
public interface IBaseService<T extends IBaseDao<E>, E extends BaseEntity> {

    E get(Long id);

    void delete(Long id);

    long insert(E item);

    void batchInsert(List<E> list);

    void modifyById(E item);

    Page<E> getPage(Map<String, Object> param) throws KyleExceptioin;

    Integer getCount(Map<String, Object> param);

    List<E> getPageList(Map<String, Object> param) throws KyleExceptioin;

    List<E> getList(Map<String, Object> param) throws KyleExceptioin;

    List<E> getByField(String filedName, Object value);

    E getSingleByField(String filedName, Object value);
}
