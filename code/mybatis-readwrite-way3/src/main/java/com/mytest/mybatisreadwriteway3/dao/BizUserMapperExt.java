package com.mytest.mybatisreadwriteway3.dao;

import com.mytest.mybatisreadwriteway3.dao.auto.BizUserMapper;
import com.mytest.mybatisreadwriteway3.model.entity.BizUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BizUserMapperExt extends BizUserMapper {
    List<BizUser> selectByCondition(@Param("condition") HashMap<String, Object> condition);
}