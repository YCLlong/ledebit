package com.ledebit.dao;

import com.ledebit.pojo.loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface loanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(loan record);

    int insertSelective(loan record);

    loan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(loan record);

    int updateByPrimaryKey(loan record);

    List<loan> selectall();

    List<loan>selectByLoanidandDebitname(@Param("debitname") String debitname,@Param("loanid") int loanid);

    int updatestatus(@Param("loanid") int loanid,@Param("loanid")int status);


}