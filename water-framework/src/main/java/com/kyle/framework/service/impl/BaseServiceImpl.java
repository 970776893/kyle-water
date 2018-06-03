package com.kyle.framework.service.impl;


import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.Page;
import com.kyle.framework.service.IBaseService;
import com.kyle.framework.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-2-23.
 */
public abstract class BaseServiceImpl<T extends IBaseDao<E>, E extends BaseEntity> implements IBaseService<T, E> {

    @Autowired
    protected T dao;

    @Value("${single.query.max.item}")
    protected Integer singleQueryMaxLength;

    @Override
    public E get(Long id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public long insert(E item) {
        handlerCreateUser(item);
        item.setIsDel(false);
        dao.insertSelective(item);
        return item.getId();
    }

    @Override
    @Transactional
    public void batchInsert(List<E> list) {
        for(E item: list) {
            handlerCreateUser(item);
            item.setIsDel(false);
        }
        dao.batchInsert(list);

    }

    @Override
    @Transactional
    public void modifyById(E item) {
        dao.updateSelectiveById(item);
    }

    @Override
    public Page<E> getPage(Map<String, Object> param){
        PageUtils.handlerPage(param);
        Integer total = getCount(param);
        List<E> list = getPageList(param);

        Page<E> modelPage = new Page<>();
        modelPage.setPageNo(PageUtils.getPageNo(param));
        modelPage.setPageSize(PageUtils.getPageSize(param));
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
        hendleListMax(param);
        return dao.listByParams(param);
    }

    @Override
    public List<E> getList(Map<String, Object> param) {
        hendleListMax(param);
        return dao.listByParams(param);
    }

    /**
     * 查询的最大条目限制
     * @param params
     * @throws KyleExceptioin
     */
    protected void hendleListMax(Map<String, Object> params){
        //默认分页查询
        Integer count = PageUtils.getPageSize(params);
        if (count == null) {
            //页表查询
            count = dao.countByParams(params);
        }

        if(count > singleQueryMaxLength) {
            throw new KyleExceptioin("查询数量太多");
        }
    }

    //TODO 登录模块还未做好，暂时是使用system
    protected void handlerCreateUser(BaseEntity item) {
        item.setCreateAt(System.currentTimeMillis());
        item.setCreateBy("system");
        item.setCreateName("system");
    }

}
