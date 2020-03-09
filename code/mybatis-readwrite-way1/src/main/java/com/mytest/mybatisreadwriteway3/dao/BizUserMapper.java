package com.mytest.mybatisreadwriteway3.dao;

import com.mytest.mybatisreadwriteway3.model.entity.BizUser;

public interface BizUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizUser record);

    int insertSelective(BizUser record);

    BizUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizUser record);

    int updateByPrimaryKey(BizUser record);
}