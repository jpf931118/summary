package com.jipf.aop.proxy;

import com.jipf.aop.aspect.IAspectHandler;
import com.jipf.aop.proxy.impl.BeanInvocationHandler;

import java.lang.reflect.Proxy;

public class ProxyBeanFactory {

    public static Object newProxyBean(Object target, IAspectHandler iAspectHandler, Class<?>[] interfaces) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , interfaces
                , new BeanInvocationHandler(target, iAspectHandler));
    }
}
