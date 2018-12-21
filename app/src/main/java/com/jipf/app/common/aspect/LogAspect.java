package com.jipf.app.common.aspect;

import com.jipf.aop.annotation.JAspect;
import com.jipf.aop.annotation.JPointcut;
import com.jipf.aop.core.IAspect;
import com.jipf.app.common.annotation.Log;
import com.jipf.core.common.log.LoggerFactory;
import com.jipf.core.common.log.impl.Logger;
import com.jipf.ioc.annotation.JComponent;

@JAspect
@JComponent
public class LogAspect implements IAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @JPointcut(clazz = Log.class)
    public void Pointcut() {

    }

    public void before(String info) {
        logger.info(info + "...before...");
    }

    public void after(String info) {
        logger.info(info + "...after...");
    }
}
