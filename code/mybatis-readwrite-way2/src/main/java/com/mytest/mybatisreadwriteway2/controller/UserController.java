package com.mytest.mybatisreadwriteway2.controller;

import com.mytest.mybatisreadwriteway2.common.base.ApiResponse;
import com.mytest.mybatisreadwriteway2.model.req.AddUserReq;
import com.mytest.mybatisreadwriteway2.model.req.QueryUserReq;
import com.mytest.mybatisreadwriteway2.model.resp.AddUserResp;
import com.mytest.mybatisreadwriteway2.model.resp.QueryUserResp;
import com.mytest.mybatisreadwriteway2.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ApiResponse<AddUserResp> add(@RequestBody AddUserReq req) {
        return ApiResponse.success(userService.addUser(req));
    }

    @ApiOperation("查询用户")
    @PostMapping("/query")
    public ApiResponse<QueryUserResp> queryUser(@RequestBody QueryUserReq req) {
        return ApiResponse.success(userService.queryUser(req));
    }

    @ApiOperation("新增用户并且查询新添加的用户")
    @PostMapping("/addAndQuery")
    public ApiResponse<AddUserResp> addAndQuery(@RequestBody AddUserReq req) {
        return ApiResponse.success(userService.addAndQueryUser(req));
    }
}
