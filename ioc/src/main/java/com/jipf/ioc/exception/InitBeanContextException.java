package com.jipf.ioc.exception;

public class InitBeanContextException extends RuntimeException{

    public InitBeanContextException(String msg){
        super(msg);
    }

    public InitBeanContextException(Throwable cause){
        super(cause);
    }
}
