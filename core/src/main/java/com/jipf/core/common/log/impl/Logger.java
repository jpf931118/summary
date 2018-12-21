package com.jipf.core.common.log.impl;

import com.jipf.core.common.log.AbsLogger;

public class Logger extends AbsLogger {

    private Class<?> clazz;

    public Logger(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void info(String s) {
        String info = clazz.getCanonicalName() + ":" + s;
        super.info(info);
    }
}
