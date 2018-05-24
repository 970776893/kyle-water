package com.kyle.framework.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kyle.framework.annotation.SystemLog;

/**
 * 日志切面
 * Created by zhangkai on 2/26/16.
 */
public class SystemLogInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemLogInterceptor.class);

    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        StringBuilder logMessage = new StringBuilder(getLogDescription(pjp));
        long timeStart = System.currentTimeMillis();
        // input value

        String classPath = pjp.getTarget().getClass().getName();
        if(pjp.getTarget() instanceof Proxy) {
            classPath = pjp.getSignature().getDeclaringType().getName();
        }
        logMessage.append(MethodLogConstants.NEW_LINE + MethodLogConstants.CLASS_LABEL + classPath + MethodLogConstants.NEW_LINE);
        String method = pjp.getSignature().getName();
        logMessage.append(MethodLogConstants.METHOD_LABEL + method + MethodLogConstants.NEW_LINE);
        logMessage.append(MethodLogConstants.INPUT_LABEL);
        logMessage.append(JSON.toJSON(pjp.getArgs()) + MethodLogConstants.NEW_LINE);

        //调用核心逻辑
        Object retVal = pjp.proceed();

        //output
        String retValJson = JSON.toJSONString(retVal);
        logMessage.append(MethodLogConstants.OUTPUT_LABEL);
        if(retValJson.length() > MethodLogConstants.OUT_PUT_MAX_LENGTH) {
            logMessage.append(retValJson, 0, MethodLogConstants.OUT_PUT_MAX_LENGTH)
                .append(MethodLogConstants.OUTPUT_ETC);
        }else{
            logMessage.append(retValJson);
        }
        logMessage.append(MethodLogConstants.NEW_LINE);

        //costtime
        logMessage.append(MethodLogConstants.COSTTIME_LABEL)
            .append(System.currentTimeMillis() - timeStart)
            .append(MethodLogConstants.COSTTIME_UNIT);

        LOGGER.info(logMessage.toString());
        return retVal;
    }

    private static class MethodLogConstants {
        private static final int OUT_PUT_MAX_LENGTH = 1024;
        private static String NEW_LINE = "\n";
        private static String CLASS_LABEL = "  class    : ";
        private static String METHOD_LABEL = "  method   : ";
        private static String INPUT_LABEL = "  input    : ";
        private static String OUTPUT_LABEL = "  output   : ";
        private static String OUTPUT_ETC = "...";
        private static String COSTTIME_LABEL = "  costtime : ";
        private static String COSTTIME_UNIT = "ms";
        private static String EMPTY = "";

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param pjp 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getLogDescription(ProceedingJoinPoint pjp)  throws Exception {
        String targetName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] arguments = pjp.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = MethodLogConstants.EMPTY;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemLog. class).description();
                    break;
                }
            }
        }
        return description;
    }
}
