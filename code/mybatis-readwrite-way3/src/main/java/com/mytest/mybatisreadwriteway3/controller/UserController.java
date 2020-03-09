package com.mytest.mybatisreadwriteway3.controller;

import com.mytest.mybatisreadwriteway3.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }


}