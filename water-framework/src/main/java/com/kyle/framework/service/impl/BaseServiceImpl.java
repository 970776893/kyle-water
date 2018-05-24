package com.kyle.framework.service.impl;


import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.model.ModelPage;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.service.IBaseService;
import com.kyle.framework.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-2-23.
 */
public abstract class BaseServiceImpl<T extends IBaseDao<E>, E extends BaseEntity> implements IBaseService<T, E> {

    @Autowired
    protected T dao;

    @Override
    public E get(Long id) {
        return dao.selectById(id);
    }

    @Override
    public ModelResult<String> delete(Long id) {
        ModelResult<String> result = new ModelResult<>("删除成功");
        dao.deleteById(id);
        return result;
    }

    @Override
    public ModelResult<E> create(E item) {
        ModelResult<E> result = new ModelResult<>("创建成功");
        dao.insertSelective(item);
        result.setData(item);
        return result;
    }

    @Override
    public ModelResult<String> modifyById(E item) {
        ModelResult<String> result = new ModelResult<>("修改成功");
        dao.updateSelectiveById(item);
        return result;
    }

    @Override
    public ModelPage<E> getPage(Map<String, Object> param) {
        PageUtils.handlerPage(param);
        Integer total = getCount(param);
        List<E> list = getPageList(param);

        ModelPage<E> modelPage = new ModelPage<>();
        modelPage.setPageNo((Integer) param.get("pageNo"));
        modelPage.setPageSize((Integer) param.get("pageSize"));
        modelPage.setItemList(list);
        modelPage.setTotal(total);
        return modelPage;
    }

    @Override
    public Integer getCount(Map<String, Object> param) {
        return dao.countByParams(param);
    }

    @Override
    public List<E> getPageList(Map<String, Object> param) {
        PageUtils.handlerPage(param);
        return dao.listByParams(param);
    }

    @Override
    public List<E> getList(Map<String, Object> param) {
        return dao.listByParams(param);
    }

}
