package com.ledebit.dao;

import com.ledebit.pojo.loan;

public interface loanMapper {
    int deleteByPrimaryKey(String id);

    int insert(loan record);

    int insertSelective(loan record);

    loan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(loan record);

    int updateByPrimaryKey(loan record);
}