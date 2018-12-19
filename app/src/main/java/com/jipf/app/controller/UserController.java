package com.jipf.app.controller;

import com.jipf.app.service.UserService;
import com.jipf.ioc.annotation.JAutowired;
import com.jipf.ioc.annotation.JController;

@JController
public class UserController {

    @JAutowired
    private UserService userService;

    public void print(String str){
        userService.print(str);
    }
}
