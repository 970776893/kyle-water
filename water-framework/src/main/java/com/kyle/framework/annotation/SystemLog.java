package com.kyle.framework.annotation;

import java.lang.annotation.*;

/**
 * @author YiXinCapital -- zhangkai02
 *         5/24/18 22:24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String description() default "";
}
