package com.kyle.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangkai on 10/13/16.
 */
public class PageUtils {
    /**
     * 处理分页，将pageNo和pageSize转换成offset和size，放到params中
     *
     * @param params
     * @return
     */
    public static Map<String, Object> handlerPage(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        //默认值
        if (!params.containsKey("pageNo")) {
            params.put("pageNo", 1);
        }
        if (!params.containsKey("pageSize")) {
            params.put("pageSize", 15);
        }
        //类型转换
        if (!(params.get("pageNo") instanceof Integer)) {
            params.put("pageNo", Integer.parseInt(String.valueOf(params.get("pageNo"))));
        }
        if (!(params.get("pageSize") instanceof Integer)) {
            params.put("pageSize", Integer.parseInt(String.valueOf(params.get("pageSize"))));
        }
        //设置参数
        Integer pageNo = (Integer) params.get("pageNo");
        Integer pageSize = (Integer) params.get("pageSize");
        //起始位置（从0开始）
        params.put("offset", (pageNo - 1) * pageSize);
        //数量
        params.put("size", pageSize);
        return params;
    }
}
