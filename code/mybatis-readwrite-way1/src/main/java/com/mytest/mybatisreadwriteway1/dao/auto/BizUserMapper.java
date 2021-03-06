package com.mytest.mybatisreadwriteway1.dao.auto;

import com.mytest.mybatisreadwriteway1.model.entity.BizUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface BizUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    int insert(BizUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    int insertSelective(BizUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    BizUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    int updateByPrimaryKeySelective(BizUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user
     *
     * @mbg.generated Wed Mar 11 20:06:24 CST 2020
     */
    int updateByPrimaryKey(BizUser record);

    List<BizUser> selectByCondition(@Param("condition") HashMap<String, Object> condition);
}