package com.jipf.app.common.aspect;

import com.jipf.aop.annotation.JAspect;
import com.jipf.aop.annotation.JPointcut;
import com.jipf.aop.core.IAspect;
import com.jipf.app.common.annotation.Log;
import com.jipf.ioc.annotation.JComponent;

@JAspect
@JComponent
public class LogAspect implements IAspect {

    @JPointcut(clazz = Log.class)
    public void Pointcut(){

    }

    public void before() {
        System.out.println(this.getClass().getSimpleName() + "...before...");
    }

    public void after() {
        System.out.println(this.getClass().getSimpleName() +  "...after...");
    }
}
