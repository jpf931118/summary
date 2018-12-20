package com.jipf.aop.exception;

public class AspectHandlerException extends RuntimeException{

    public AspectHandlerException(String msg){
        super(msg);
    }

    public AspectHandlerException(Throwable cause){
        super(cause);
    }
}
