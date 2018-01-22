package com.ledebit.dao;

import com.ledebit.pojo.loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface loanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(loan record);

    int checkno(long recordno);

    int insertSelective(loan record);

    loan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(loan record);

    int updateByPrimaryKey(loan record);

    List<loan> selectallfromloan();

    List<loan> selectoverduefromloan(Integer status);

    List<loan>selectByLoanidandDebitname(@Param("debitname") String debitname,@Param("debitid") Integer debitid);

    int updatestatus(@Param("loanid") int loanid,@Param("status")int status);

    List<loan> selectbydebitid(Integer debitid);


}