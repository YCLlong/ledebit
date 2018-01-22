package com.ledebit.service;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.PageinfoResponse;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.debit;
import com.ledebit.vo.Debitandrecordvo;
import com.ledebit.vo.debitvo;

import java.util.List;


/**
 * Created by Administrator on 2017/11/30.
 */
public interface IDebitService {
    ServiceResponse saveorupdate(debit debitinfo);

    ServiceResponse changeorupdaterank(int rank,String username);

    PageinfoResponse<List> searchdebitbynameortelorboth(String name, String tel, Integer pagenum, Integer pagesize);

    PageinfoResponse<List> listall(int pagenum,int pagesize);

    ServiceResponse deletedebit(int id);

    ServiceResponse<Debitandrecordvo> personalfrom(int id);

}
