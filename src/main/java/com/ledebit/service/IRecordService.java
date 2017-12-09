package com.ledebit.service;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.loan;
import com.ledebit.vo.Recordvo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
public interface IRecordService {
    ServiceResponse<Recordvo> add(loan loaninfo);

    ServiceResponse<PageInfo> listall(int pagenum, int pagesize);

    ServiceResponse<PageInfo> searchRecord(int pagenum,int pagesize,Integer loanid,String debitname);

    ServiceResponse statuschange(List<Recordvo> recordvoList);

    ServiceResponse pushforward(List<Recordvo> recordvoList);
}
