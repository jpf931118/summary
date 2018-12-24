package com.jipf.aop;

import com.jipf.aop.aspect.IAspectHandler;
import com.jipf.aop.aspect.impl.AspectHandler;
import com.jipf.aop.exception.AspectHandlerException;
import com.jipf.aop.proxy.ProxyBeanFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class AopBeanContext {

    /**
     * 根据切点创建含有代理类的ioc bean容器
     *
     * @return
     */
    public static void createProxyBeanContext(Map<String, Object> iocBeans) {

        IAspectHandler iAspectHandler = new AspectHandler(iocBeans);

        /* 处理需要代理的类 生成代理实例替换原始对象**/
        doProxy(iocBeans, iAspectHandler);
    }

    private static void doProxy(Map<String, Object> iocBeans, IAspectHandler iAspectHandler) {
        Iterator<Map.Entry<String, Object>> iterator = iocBeans.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            Class<?> clazz = entry.getValue().getClass();
            boolean status = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    Class<?> cl = annotation.annotationType();
                    if (iAspectHandler.getAspectInstance(cl.getCanonicalName()) != null) {//需要生成代理类
                        status = true;
                        break;
                    }
                }
            }
            if (status) {//需要生成代理类
                Class<?>[] classes = clazz.getInterfaces();
                if (classes.length == 0) {
                    throw new AspectHandlerException(clazz.getCanonicalName() + " proxy instance must be implements interface");
                }
                Object proxy = ProxyBeanFactory.newProxyBean(entry.getValue(), iAspectHandler, classes);
                iocBeans.put(entry.getKey(), proxy);
            }
        }
    }
}
