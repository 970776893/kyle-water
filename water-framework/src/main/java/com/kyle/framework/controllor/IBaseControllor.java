package com.kyle.framework.controllor;


import com.kyle.framework.annotation.SystemLog;
import com.kyle.framework.dao.IBaseDao;
import com.kyle.framework.entity.BaseEntity;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.Page;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.service.IBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangkai on 16-1-19.
 */
@Log4j2
public abstract class IBaseControllor<E extends BaseEntity, F extends IBaseDao<E>, T extends IBaseService<F, E>> {

    @Autowired
    protected T service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @ResponseBody
    @RequestMapping(value = "page" , method = {RequestMethod.GET})
    @SystemLog(description = "查询分页信息")
    public ModelResult<Page<E>> page() {
        ModelResult<Page<E>> result = new ModelResult<>("请求成功");
        try {
            Map<String, Object> params = new HashMap<>();
            for (Object keyObj : request.getParameterMap().keySet()) {
                String key = (String) keyObj;
                params.put(key, request.getParameter(key));
            }
            Page<E> pageData = service.getPage(params);
            result.setData(pageData);
            result.success();
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("查询失败", k);
        } catch (Throwable e) {
            result.failure("查询异常");
            log.error("查询异常", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @SystemLog(description = "查询列表信息")
    public ModelResult<List<E>> list() {
        ModelResult<List<E>> result = new ModelResult<>("请求成功");
        try {
            Map<String, Object> params = new HashMap<>();
            for (Object keyObj : request.getParameterMap().keySet()) {
                String key = (String) keyObj;
                params.put(key, request.getParameter(key));
            }
            List<E> pageData = service.getList(params);
            result.setData(pageData);
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("查询失败", k);
        } catch (Throwable e) {
            result.failure("查询异常");
            log.error("查询异常", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @SystemLog(description = "更新内容")
    public ModelResult<String> update(@RequestBody E item) {
        ModelResult<String> result = new ModelResult<>("更新成功");
        try {
            service.modifyById(item);
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("更新失败", k);
        } catch (Throwable e) {
            result.failure("更新异常");
            log.error("更新异常", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @SystemLog(description = "新增内容")
    public ModelResult<Long> create(@RequestBody E item) {
        ModelResult<Long> result = new ModelResult<>("新增成功");
        try {
            long id = service.create(item);
            result.setData(id);
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("新增失败", k);
        } catch (Throwable e) {
            result.failure("新增异常");
            log.error("新增异常", e);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    @SystemLog(description = "根据ID获取信息")
    public ModelResult<E> getById(@RequestParam Long id) {
        ModelResult<E> result = new ModelResult<>("请求成功");
        try {
            E entity = service.get(id);
            result.setData(entity);
        } catch (KyleExceptioin k) {
            result.failure(k.getMessage());
            log.error("查询失败", k);
        } catch (Throwable e) {
            result.failure("查询异常");
            log.error("查询异常", e);
        }
        return result;
    }
}
