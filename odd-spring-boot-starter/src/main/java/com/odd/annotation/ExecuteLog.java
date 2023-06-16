package com.odd.annotation;

import java.lang.annotation.*;

/**
 * 打印方法的执行时间、参数、返回结果
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecuteLog {

    boolean executeTime() default false;

    boolean param() default false;

    boolean result() default false;

}
