package com.mytest.mybatisreadwriteway3.model.req;

import lombok.Data;

import java.util.Date;

@Data
public class AddUserReq {

    private String name;

    private Integer age;

    private String desc;

    private String createBy;

    private Date createTime;

    private String phone;

    private String email;
}
