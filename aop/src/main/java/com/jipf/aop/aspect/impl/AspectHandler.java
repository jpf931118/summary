package com.jipf.aop.aspect.impl;

import com.jipf.aop.aspect.AbsAspectHandler;

import java.util.List;
import java.util.Map;

public class AspectHandler extends AbsAspectHandler {

    public AspectHandler(Map<String, Object> iocBeans) {

        /* 获取带有@JAspect的类**/
        List<Object> aspectBeans = this.filterAspect(iocBeans);
        /* 解析切面类**/
        this.parseAspect(aspectBeans);

    }
}
