package com.mytest.mybatisreadwriteway1.model.resp;

import com.mytest.mybatisreadwriteway1.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}
