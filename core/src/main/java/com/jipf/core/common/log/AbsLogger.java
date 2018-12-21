package com.jipf.core.common.log;

public abstract class AbsLogger implements ILogger{

    @Override
    public void info(String s) {
        System.out.println(s);
    }
}
