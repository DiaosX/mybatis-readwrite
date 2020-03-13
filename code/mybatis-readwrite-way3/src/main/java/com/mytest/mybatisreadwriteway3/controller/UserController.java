package com.mytest.mybatisreadwriteway3.controller;

import com.mytest.mybatisreadwriteway3.common.base.ApiResponse;
import com.mytest.mybatisreadwriteway3.model.req.AddUserReq;
import com.mytest.mybatisreadwriteway3.model.req.QueryUserReq;
import com.mytest.mybatisreadwriteway3.model.resp.AddUserResp;
import com.mytest.mybatisreadwriteway3.model.resp.QueryUserResp;
import com.mytest.mybatisreadwriteway3.service.UserService;
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
}
