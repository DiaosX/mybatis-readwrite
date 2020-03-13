package com.mytest.mybatisreadwriteway3.service;

import com.mytest.mybatisreadwriteway3.dao.BizUserMapperExt;
import com.mytest.mybatisreadwriteway3.model.entity.BizUser;
import com.mytest.mybatisreadwriteway3.model.req.AddUserReq;
import com.mytest.mybatisreadwriteway3.model.req.QueryUserReq;
import com.mytest.mybatisreadwriteway3.model.resp.AddUserResp;
import com.mytest.mybatisreadwriteway3.model.resp.QueryUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private BizUserMapperExt bizUserMapperExt;

    public QueryUserResp queryUser(QueryUserReq req) {
        QueryUserResp resp = new QueryUserResp();
        HashMap<String, Object> condition = new HashMap<>();
        List<BizUser> userList = bizUserMapperExt.selectByCondition(condition);
        resp.setUserList(userList);
        return resp;
    }

    public AddUserResp addUser(AddUserReq req) {
        AddUserResp resp = new AddUserResp();
        BizUser user = new BizUser();
        user.setAge(req.getAge());
        user.setDesc(req.getDesc());
        user.setName(req.getName());
        user.setCreateBy("admin");
        user.setCreateTime(new Date());
        user.setPhone(req.getPhone());
        user.setDisabled(false);
        user.setEmail(req.getEmail());
        bizUserMapperExt.insertSelective(user);
        return resp;
    }
}
