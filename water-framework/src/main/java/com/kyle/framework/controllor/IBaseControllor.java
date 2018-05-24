package com.kyle.framework.controllor;


import com.kyle.framework.annotation.SystemLog;
import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.model.ModelPage;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-1-19.
 */
public abstract class  IBaseControllor <E extends BaseEntity, F extends IBaseDao<E>, T extends IBaseService<F, E>> {

    @Autowired
    protected T service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @ResponseBody
    @RequestMapping("page")
    @SystemLog(description = "查询分页信息")
    public ModelResult<ModelPage<E>> page(){
        Map<String,Object> params = new HashMap<>();
        for(Object keyObj : request.getParameterMap().keySet()){
            String key = (String) keyObj;
            params.put(key, request.getParameter(key));
        }
        ModelPage<E> pageData = service.getPage(params);
        ModelResult<ModelPage<E>> result = new ModelResult<>("请求成功");
        result.setData(pageData);
        return result;
    }

    @ResponseBody
    @RequestMapping("list")
    @SystemLog(description = "查询列表信息")
    public ModelResult<List<E>> list(){
        Map<String,Object> params = new HashMap<>();
        for(Object keyObj : request.getParameterMap().keySet()){
            String key = (String) keyObj;
            params.put(key, request.getParameter(key));
        }
        List<E> pageData = service.getList(params);
        ModelResult<List<E>> result = new ModelResult<>("请求成功");
        result.setData(pageData);
        return result;
    }

    @ResponseBody
    @RequestMapping("update")
    @SystemLog(description = "更新内容")
    public ModelResult<String> update(@RequestBody E item){
        return service.modifyById(item);
    }

    @ResponseBody
    @RequestMapping("create")
    @SystemLog(description = "新增内容")
    public ModelResult<E> create(@RequestBody E item){
        return service.create(item);
    }


    @ResponseBody
    @RequestMapping("getById")
    @SystemLog(description = "根据ID获取信息")
    public ModelResult<E> getById(@RequestParam(required = true) Long id){
        E entity = service.get(id);
        ModelResult<E> result = new ModelResult<>("请求成功");
        result.setData(entity);
        return result;
    }
}
