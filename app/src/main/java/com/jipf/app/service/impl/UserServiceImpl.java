package com.jipf.app.service.impl;

import com.jipf.app.common.annotation.Log;
import com.jipf.app.service.UserService;
import com.jipf.ioc.annotation.JService;

@JService
public class UserServiceImpl implements UserService {

    @Log
    public void print(String str) {
        System.out.println(str);
    }
}
