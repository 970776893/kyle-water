package com.kyle.water.activity.controller;

import com.kyle.framework.annotation.SystemLog;
import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.framework.exception.KyleExceptioin;
import com.kyle.framework.model.ModelResult;
import com.kyle.water.activity.dao.IActivityDao;
import com.kyle.water.activity.entity.ActivityDetail;
import com.kyle.water.activity.entity.ActivityEntity;
import com.kyle.water.activity.service.IActivityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kyle.
 */
@Controller
@RequestMapping("/activity")
@Log4j2
public class ActivityController extends IBaseControllor<ActivityEntity, IActivityDao, IActivityService> {


    /**
     * 获取活动的详情
     *
     * @param code 获取编码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDetailByCode", method = {RequestMethod.GET})
    @SystemLog(description = "获取活动详情")
    public ModelResult<ActivityDetail> getDetailByCode(@RequestParam String code) {
        ModelResult<ActivityDetail> result = new ModelResult<>();
        try {
            ActivityDetail deail = service.getDetailByCode(code);

            if (deail != null) {
                result.setData(deail);
                result.success();
            } else {
                result.failure("活动不存在");
            }
        } catch (KyleExceptioin k) {
            log.error("查询失败", k);
            result.failure(k.getMessage());
        } catch (Throwable e) {
            log.error("获取活动异常", e);
            result.failure("获取异常");
        }
        return result;
    }

    /**
     * 创建活动
     *
     * @param activityDetail 获取编码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @SystemLog(description = "创建活动")
    public ModelResult<String> create(@RequestBody ActivityDetail activityDetail) {
        ModelResult<String> result = new ModelResult<>();
        try {
            String code = service.create(activityDetail);
            result.setData(code);
            result.success();
        } catch (KyleExceptioin k) {
            log.error("创建活动失败", k);
            result.failure(k.getMessage());
        } catch (Throwable e) {
            log.error("创建活动活动异常", e);
            result.failure("创建活动活动异常");
        }
        return result;
    }

}
