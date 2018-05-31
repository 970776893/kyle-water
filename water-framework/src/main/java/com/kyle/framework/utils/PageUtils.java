package com.kyle.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangkai on 10/13/16.
 */
public class PageUtils {

    /**
     * 分页查询-当前页的字段定义
     */
    public static String PAGE_NO_FIELD_DEFINATION = "pageNo";
    /**
     * 分页查询-页大小字段定义
     */
    public static String PAGE_SIZE_FIELD_DEFINATION = "pageSize";

    private static int PAGE_NO_DEFAULT = 1;

    private static int page_size_default = 15;

    private static String LIMIT_OFFSET_FILE_DEFINITION = "offset";

    private static String LIMIT_SIZE_FILE_DEFINITION = "size";


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
        if (!params.containsKey(PAGE_NO_FIELD_DEFINATION)) {
            params.put(PAGE_NO_FIELD_DEFINATION, PAGE_NO_DEFAULT);
        }
        if (!params.containsKey(PAGE_SIZE_FIELD_DEFINATION)) {
            params.put(PAGE_SIZE_FIELD_DEFINATION, page_size_default);
        }

        //设置参数
        Integer pageNo = (Integer) params.get(PAGE_NO_FIELD_DEFINATION);
        Integer pageSize = (Integer) params.get(PAGE_SIZE_FIELD_DEFINATION);
        //起始位置（从0开始）
        params.put(LIMIT_OFFSET_FILE_DEFINITION, (pageNo - 1) * pageSize);
        //数量
        params.put(LIMIT_SIZE_FILE_DEFINITION, pageSize);
        return params;
    }

    /**
     * 从params获取pageSize 返回
     * @param params
     * @return
     */
    public static Integer getPageSize(Map<String, Object> params){
        return (Integer) params.get(PAGE_SIZE_FIELD_DEFINATION);
    }
    /**
     * 从params获取pageNo 返回
     * @param params
     * @return
     */
    public static Integer getPageNo(Map<String, Object> params) {
        return (Integer) params.get(PAGE_NO_FIELD_DEFINATION);

    }
}
