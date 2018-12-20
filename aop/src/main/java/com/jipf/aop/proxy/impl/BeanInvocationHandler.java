package com.jipf.aop.proxy.impl;

import com.jipf.aop.aspect.IAspectHandler;
import com.jipf.aop.proxy.AbsBeanInvocationHandler;

public class BeanInvocationHandler extends AbsBeanInvocationHandler {

    public BeanInvocationHandler(Object target,IAspectHandler iAspectHandler) {
        super(target,iAspectHandler);
    }
}
