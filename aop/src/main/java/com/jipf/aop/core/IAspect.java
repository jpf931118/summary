package com.jipf.aop.core;

/**
 * 切面顶级接口,自定义切面类必须实现该接口
 * @author jipf
 */
public interface IAspect {

    /**
     * 前置执行
     */
    public void before();

    /**
     * 后置执行
     */
    public void after();
}
