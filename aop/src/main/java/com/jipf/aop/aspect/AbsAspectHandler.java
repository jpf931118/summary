package com.jipf.aop.aspect;

import com.jipf.aop.annotation.JAspect;
import com.jipf.aop.annotation.JPointcut;
import com.jipf.aop.core.IAspect;
import com.jipf.aop.exception.AspectHandlerException;

import java.lang.reflect.Method;
import java.util.*;

public abstract class AbsAspectHandler implements IAspectHandler{

    /* key=切点getCanonicalName value=切面类实例**/
    private Map<String, Object> aspectPointMap = new HashMap<>();

    @Override
    public Object getAspectInstance(String pointCanonicalName) {
        return this.aspectPointMap.get(pointCanonicalName);
    }

    protected List<Object> filterAspect(Map<String, Object> iocBeans) {
        List<Object> list = new ArrayList<Object>();
        Iterator<Map.Entry<String, Object>> iterator = iocBeans.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (entry.getValue().getClass().isAnnotationPresent(JAspect.class)) {
                list.add(entry.getValue());
                /* 移除切面类**/
                iterator.remove();
            }
        }
        return list;
    }

    protected void parseAspect(List<Object> aspectBeans) {
        aspectBeans.stream().forEach(aspect -> {
            Class<?> clazz = aspect.getClass();
            this.checkIAspect(clazz);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(JPointcut.class)) {
                    JPointcut jPointcut = method.getAnnotation(JPointcut.class);
                    Class<?> annotation = jPointcut.clazz();
                    if (!annotation.isAnnotation()) {
                        throw new AspectHandlerException("@JPointcut value must be annotation!");
                    }
                    this.aspectPointMap.put(annotation.getCanonicalName(), aspect);
                }
            }
        });
    }

    /**
     * 定义@JAspect的类必须实现IAspect接口
     *
     * @param clazz
     * @return
     */
    private void checkIAspect(Class<?> clazz) {
        Class<?>[] cl = clazz.getInterfaces();
        boolean status = false;
        for (Class<?> c : cl) {
            if (c == IAspect.class) {
                status = true;
            }
        }
        if (!status) {
            throw new AspectHandlerException(clazz.getCanonicalName() + " not implements IAspect !");
        }
    }
}
