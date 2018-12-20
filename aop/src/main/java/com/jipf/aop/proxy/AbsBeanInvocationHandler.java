package com.jipf.aop.proxy;

import com.jipf.aop.aspect.IAspectHandler;
import com.jipf.aop.core.IAspect;
import com.jipf.aop.exception.AspectHandlerException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsBeanInvocationHandler implements InvocationHandler {

    /* 代理目标对象**/
    private Object target;

    private IAspectHandler iAspectHandler;

    protected AbsBeanInvocationHandler(Object target, IAspectHandler iAspectHandler) {
        this.target = target;
        this.iAspectHandler = iAspectHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        /* 获取该方法切点对应的切面实例**/
        List<Object> instances = this.getAspectInstances(method.getAnnotations());

        /* 前置处理**/
        this.execAspectInstance("before",instances);

        Object result = null;
        try {
            result = method.invoke(this.target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AspectHandlerException("proxy invoke method exception!");
        }

        /* 后置处理**/
        this.execAspectInstance("after",instances);


        return result;
    }

    private List<Object> getAspectInstances(Annotation[] annotations) {
        List<Object> instances = new ArrayList<>();
        for (Annotation annotation : annotations){
            Class<?> clazz = annotation.getClass();
            Object instance = this.iAspectHandler.getAspectInstance(clazz.getCanonicalName());
            if (instance != null) {
                instances.add(instance);
            }
        }
        return instances;
    }

    /**
     * 执行切面实例方法
     * @param cmd
     * @param instances
     */
    private void execAspectInstance(String cmd,List<Object> instances){
        instances.stream().forEach(instance->{
            IAspect iAspect = (IAspect)instance;
            switch (cmd){
                case "before":
                    iAspect.before();
                    break;
                case "after":
                    iAspect.after();
                    break;
                default:
                    break;
            }
        });
    }
}
