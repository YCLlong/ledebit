package com.ledebit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ledebit.common.Const;
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
                debitinfo.setCreditrank(Const.creditranksenum.ROOKIE.getCode());
//                return ServiceResponse.createbysuccessmsg("测试输入的数据为"+debitinfo.getAge());
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
//        return null;
    }
    public ServiceResponse changeorupdaterank(int creditrank,String name){
        debit debitinfo=debitmapper.selectByUserName(name);
        if(debitinfo==null){
            return ServiceResponse.createbyerror("该贷款人不存在");
        }else {

            debit debitupdate=new debit();
            debitupdate.setId(debitinfo.getId());
            debitupdate.setCreditrank(creditrank);
            debitmapper.updateByPrimaryKeySelective(debitupdate);
            return ServiceResponse.createbysuccessmsg("该用户信用等级已更变为"+Const.creditranksenum.codeof(creditrank).getValue());
        }
    }
    public ServiceResponse deletedebit(int id){
        debit debitinfo=debitmapper.selectByPrimaryKey(id);
        if(debitinfo==null){
            return ServiceResponse.createbyerror("该贷款人不存在");
        }else {

            debitmapper.deleteByPrimaryKey(id);
            return ServiceResponse.createbysuccess("贷款人信息已删除");
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
        List<debit> debitslist=debitmapper.selectall();
        //测试用
//          debit debitinfos=debitmapper.selectByPrimaryKey(127);
//        return ServiceResponse.createbysuccessmsg("得到的数据有"+debitinfos.getName());
        List<debitvo> debitvoList=new ArrayList<>();
        for(debit debitinfo:debitslist){
            debitvo debitvos=assemblefordeitvo(debitinfo);
            debitvoList.add(debitvos);
        }
        PageHelper.startPage(pagenum,pagesize);
        PageInfo pageInfo=new PageInfo(debitslist);
        pageInfo.setList(debitvoList);

        return  ServiceResponse.createbysuccess(pageInfo);
    }

    private debitvo assemblefordeitvo(debit debitinfo){
        debitvo debitvos=new debitvo();
        debitvos.setId(debitinfo.getId());
        debitvos.setAge(debitinfo.getAge());
//        debitvos.setCreditrank(debitinfo.getCreditrank());
        if(debitinfo.getCreditrank()==Const.creditranksenum.ROOKIE.getCode()){
            debitvos.setCreditrank(Const.creditranksenum.ROOKIE.getValue());
        }else if(debitinfo.getCreditrank()==Const.creditranksenum.COPPER.getCode()){
            debitvos.setCreditrank(Const.creditranksenum.COPPER.getValue());
        }else if(debitinfo.getCreditrank()==Const.creditranksenum.SILIVER.getCode()) {
            debitvos.setCreditrank(Const.creditranksenum.SILIVER.getValue());
        }else if(debitinfo.getCreditrank()==Const.creditranksenum.GOLDEN.getCode()) {
            debitvos.setCreditrank(Const.creditranksenum.GOLDEN.getValue());
        }
//        debitvos.setDebittype(debitinfo.getDebittype());
        if(debitinfo.getDebittype()==Const.debitstylestatusenum.ONLINE.getCode()){
            debitvos.setDebittype(Const.debitstylestatusenum.ONLINE.getValue());
        }else if(debitinfo.getDebittype()==Const.debitstylestatusenum.OFFLINE.getCode()){
            debitvos.setDebittype(Const.debitstylestatusenum.OFFLINE.getValue());
        }
        debitvos.setEmail(debitinfo.getEmail());
        if(debitinfo.getGender()== Const.Gender.MAN){
            debitvos.setGender("男");
        }else if(debitinfo.getGender()==Const.Gender.WOMEN){
            debitvos.setGender("女");
        }
        debitvos.setJob(debitinfo.getJob());
        debitvos.setName(debitinfo.getName());
        debitvos.setTel(debitinfo.getTel());
        return debitvos;
    }
}
