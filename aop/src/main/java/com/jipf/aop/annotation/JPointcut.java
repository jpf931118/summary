package com.jipf.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切点
 * @author jipf
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JPointcut {

    /**
     * 仅支持注解方式
     * @return
     */
    Class<?> clazz();
}
