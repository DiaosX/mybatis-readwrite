package com.mytest.mybatisreadwriteway2.controller;

import com.mytest.mybatisreadwriteway2.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }
}
