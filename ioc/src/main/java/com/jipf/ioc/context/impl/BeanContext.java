package com.jipf.ioc.context.impl;

import com.jipf.ioc.common.IOCConst;
import com.jipf.ioc.context.AbsBeanContext;

/**
 * 初始化bean容器
 */
public class BeanContext extends AbsBeanContext {

    public BeanContext(String properties){
        /* 加载资源文件**/
        this.loadProperties(properties);
        /* 扫描class包**/
        this.scanPackage(this.scan_package);
        /* 初始化ioc容器**/
        this.initIoc();
        /* aop功能处理**/
        this.doAop();
        /* bean注入**/
        this.injection();
    }
}
