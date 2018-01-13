package com.ledebit.dao;

import com.ledebit.pojo.debit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface debitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(debit record);

    int insertSelective(debit record);

    debit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(debit record);

    int updateByPrimaryKey(debit record);

    debit selectByUserid(Integer userid);

    debit selectByUserName(String name);

    List<debit> selectBynameAndTel(@Param("name") String name, @Param("tel") String tel);

    List<debit> selectList();

    List<debit> selectall();
}