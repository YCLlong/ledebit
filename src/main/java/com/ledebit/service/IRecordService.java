package com.ledebit.service;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.PageinfoResponse;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.loan;
import com.ledebit.vo.Recordvo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
public interface IRecordService {
    ServiceResponse<Recordvo> add(loan loaninfo);

    PageinfoResponse<List> listall(int pagenum, int pagesize);

    PageinfoResponse<List> listoverdue(int pagenum, int pagesize);

    PageinfoResponse<List> searchRecord(int pagenum,int pagesize,Integer loanid,String debitname);

    ServiceResponse statuschange(List<Recordvo> loaninfo);

    ServiceResponse pushforward(List<Recordvo> recordvoList);
}
