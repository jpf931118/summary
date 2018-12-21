package com.jipf.core.common.log;

import com.jipf.core.common.log.impl.Logger;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }
}
