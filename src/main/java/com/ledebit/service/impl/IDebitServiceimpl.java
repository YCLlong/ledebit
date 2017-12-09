package com.ledebit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ledebit.common.ServiceResponse;
import com.ledebit.dao.UserMapper;
import com.ledebit.dao.debitMapper;
import com.ledebit.pojo.debit;
import com.ledebit.service.IDebitService;
import com.ledebit.vo.debitvo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@Service("iDebitService")
public class IDebitServiceimpl implements IDebitService {
    @Autowired
    private debitMapper debitmapper;
    @Autowired
    private UserMapper userMapper;

    public ServiceResponse saveorupdate(debit debitinfo){
        if (debitinfo==null){
            return ServiceResponse.createbyerror("参数错误");
        }else{
            if(debitinfo.getId()==null){
                int resultcount=debitmapper.insert(debitinfo);
                if(resultcount>0) {
                    return ServiceResponse.createbysuccess("添加贷款人信息成功");
                }else{
                    return ServiceResponse.createbysuccess("添加贷款人信息失败");
                }
            }else {
                int resultcount=debitmapper.updateByPrimaryKeySelective(debitinfo);
                if(resultcount>0){
                    return ServiceResponse.createbysuccess("更新贷款人信息成功");
                }else{
                    return ServiceResponse.createbysuccess("更新贷款人信息失败");
                }
            }
        }
    }
    public ServiceResponse changeorupdaterank(int rank,int userid){
        debit debitinfo=debitmapper.selectByUserid(userid);
        if(debitinfo==null){
            return ServiceResponse.createbyerror("该贷款人不存在");
        }else {
            debitinfo.setCreditrank(rank);
            debitmapper.updateByPrimaryKeySelective(debitinfo);
            return ServiceResponse.createbysuccess();
        }
    }
    public ServiceResponse<PageInfo> searchdebitbynameortelorboth(String name,String tel,Integer pagenum,Integer pagesize){
        PageHelper.startPage(pagenum,pagesize);
        if(StringUtils.isNotBlank(name)){
            name =new StringBuilder().append("%").append(name).append("%").toString();
        }
        if(StringUtils.isNotBlank(tel)){
            name =new StringBuilder().append("%").append(tel).append("%").toString();
        }
        List<debit> debitList=debitmapper.selectBynameAndTel(name,tel);
        List<debitvo> debitvoList=new ArrayList<>();
        for(debit debitinfo:debitList){
            debitvo debitvos=assemblefordeitvo(debitinfo);
            debitvoList.add(debitvos);
        }
        PageInfo pageInfo=new PageInfo(debitList);
        pageInfo.setList(debitvoList);
        return  ServiceResponse.createbysuccess(pageInfo);
    }
    public ServiceResponse<PageInfo> listall(int pagenum,int pagesize){
        PageHelper.startPage(pagenum,pagesize);
        List<debit> debitslist=debitmapper.selectList();
        List<debitvo> debitvoList=new ArrayList<>();
        for(debit debitinfo:debitslist){
            debitvo debitvos=assemblefordeitvo(debitinfo);
            debitvoList.add(debitvos);
        }
        PageInfo pageInfo=new PageInfo(debitslist);
        pageInfo.setList(debitvoList);
        return  ServiceResponse.createbysuccess(pageInfo);
    }

    private debitvo assemblefordeitvo(debit debitinfo){
        debitvo debitvos=new debitvo();
        debitvos.setId(debitinfo.getId());
        debitvos.setAge(debitinfo.getAge());
        debitvos.setCreditrank(debitinfo.getCreditrank());
        debitvos.setDebittype(debitinfo.getDebittype());
        debitvos.setEmail(debitinfo.getEmail());
        debitvos.setIncome(debitinfo.getIncome());
        debitvos.setJob(debitinfo.getJob());
        debitvos.setName(debitinfo.getName());
        debitvos.setTel(debitinfo.getTel());
        return debitvos;
    }
}
