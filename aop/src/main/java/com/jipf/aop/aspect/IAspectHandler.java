package com.jipf.aop.aspect;

public interface IAspectHandler {

    /**
     * 获取切面实例
     * @param pointCanonicalName
     * @return
     */
    public Object getAspectInstance(String pointCanonicalName);
}
