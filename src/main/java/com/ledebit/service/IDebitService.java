package com.ledebit.service;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.debit;

/**
 * Created by Administrator on 2017/11/30.
 */
public interface IDebitService {
    ServiceResponse saveorupdate(debit debitinfo);

    ServiceResponse changeorupdaterank(int rank,int userid);

    ServiceResponse<PageInfo> searchdebitbynameortelorboth(String name, String tel, Integer pagenum, Integer pagesize);

    ServiceResponse<PageInfo> listall(int pagenum,int pagesize);
}
