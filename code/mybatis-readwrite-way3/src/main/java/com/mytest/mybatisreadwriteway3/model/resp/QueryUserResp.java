package com.mytest.mybatisreadwriteway3.model.resp;

import com.mytest.mybatisreadwriteway3.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}
