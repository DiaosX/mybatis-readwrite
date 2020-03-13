package com.mytest.mybatisreadwriteway2.model.resp;

import com.mytest.mybatisreadwriteway2.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}
