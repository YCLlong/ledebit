package com.ledebit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ledebit.common.Const;
import com.ledebit.common.PageinfoResponse;
import com.ledebit.common.ServiceResponse;
import com.ledebit.dao.debitMapper;
import com.ledebit.dao.loanMapper;
import com.ledebit.pojo.loan;
import com.ledebit.service.IRecordService;
import com.ledebit.util.BigDecimalUtil;
import com.ledebit.util.DatetimeUtil;
import com.ledebit.vo.Recordvo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
@Service("iRecordService")
public class IRecordServiceimpl implements IRecordService {
    @Autowired
    private loanMapper loanmapper;
    @Autowired
    private debitMapper debitmapper;

    public PageinfoResponse<List> listall(int pagenum, int pagesize){
        List<loan> recordList=loanmapper.selectallfromloan();
        List<Recordvo> recordvoList= Lists.newArrayList();
        for(loan loans:recordList){
            Recordvo recordvo=assembleforRecordvo(loans);
            recordvoList.add(recordvo);
        }
        PageHelper.startPage(pagenum,pagesize);
        PageInfo pageresult=new PageInfo(recordList);
        pageresult.setList(recordvoList);
        return PageinfoResponse.createbysuccess("",pageresult.getTotal(),pageresult.getList());
    }
    public PageinfoResponse<List> listoverdue(int pagenum, int pagesize){
        List<loan> recordList=loanmapper.selectoverduefromloan(Const.recordstatusenum.OVERDUE.getCode());
        List<Recordvo> recordvoList= Lists.newArrayList();
        for(loan loans:recordList){
            Recordvo recordvo=assembleforRecordvo(loans);
            recordvoList.add(recordvo);
        }
        PageHelper.startPage(pagenum,pagesize);
        PageInfo pageresult=new PageInfo(recordList);
        pageresult.setList(recordvoList);
        return PageinfoResponse.createbysuccess("",pageresult.getTotal(),pageresult.getList());
    }

    public ServiceResponse<Recordvo> add(loan loaninfo){
        if(loaninfo==null){
            return  ServiceResponse.createbyerror("参数为空");
        }
         else if(loaninfo.getId()==null) {
            //新增记录为未付款状态的，后面可手动检索调整状态
            int count = loanmapper.checkno(loaninfo.getRecordNo());
            if(count>0){
                return ServiceResponse.createbyerror("此记录号已存在，请校准");
            }
            loaninfo.setRecordstatus(Const.recordstatusenum.UNPAY.getCode());
            loaninfo.setPushstatus(Const.PushStatus.NO);
            //todo 对用户名和用户id进行校准
            loan loaninsert = loaninfo;
            int cou=debitmapper.CheckIdAndUsername(loaninsert.getDebitId(),loaninsert.getDebitname());
            if(cou==0){
                return ServiceResponse.createbyerror("贷款人信息校验错误");
            }
            int resultcount = loanmapper.insert(loaninsert);
            if (resultcount > 0) {
                Recordvo recordvo =assembleforRecordvo(loaninsert);
                return ServiceResponse.createbysuccess(recordvo);
            }else {
                return ServiceResponse.createbyerror("添加记录失败");
            }
        }else {
            loan updateloan=loaninfo;
            int resultcount = loanmapper.updateByPrimaryKeySelective(updateloan);
            if(resultcount>0){
                Recordvo recordvo=assembleforRecordvo(loanmapper.selectByPrimaryKey(loaninfo.getId()));
                return ServiceResponse.createbysuccess(recordvo);
            }else {
                return ServiceResponse.createbyerror("更新记录失败");
            }
        }
    }

    public PageinfoResponse<List> searchRecord(int pagenum,int pagesize,Integer debitid,String debitname){
        if(StringUtils.isNotBlank(debitname)){
            debitname =new StringBuilder().append("%").append(debitname).append("%").toString();
        }else {
            debitname=null;
        }
        List<loan> loanList=loanmapper.selectByLoanidandDebitname(debitname,debitid);
        List<Recordvo> recordvoList=Lists.newArrayList();
        for(loan loaninfo:loanList){
            Recordvo recordvo=assembleforRecordvo(loaninfo);
            recordvoList.add(recordvo);
        }
        PageHelper.startPage(pagenum,pagesize);
        PageInfo pageresult=new PageInfo(loanList);
        pageresult.setList(recordvoList);
        return PageinfoResponse.createbysuccess("",pageresult.getTotal(),pageresult.getList());
    }
    public ServiceResponse statuschange(List<Recordvo> loaninfo){
        if(loaninfo==null){
            return ServiceResponse.createbyerror("没检测到记录，参数为空");
        }
//        List<loan> loanList=Lists.newArrayList();
        for(Recordvo recordvo:loaninfo){

            loan loan=loanmapper.selectByPrimaryKey(recordvo.getId());
//            return ServiceResponse.createbysuccess(loan.getCrAmount());
            BigDecimal bigDecimal=statuscheck(loan);
        }
        //todo 考虑是否需要返回传进列表更新之后的数据
       return ServiceResponse.createbysuccessmsg("已校验状态完成，可在查看具体信息中确认");
    }

    public ServiceResponse pushforward(List<Recordvo> recordList){
        if(recordList==null){
            return ServiceResponse.createbyerror("未检测到记录，参数为空");
        }
        for(Recordvo loand:recordList){
            loan loaninfo=loanmapper.selectByPrimaryKey(loand.getId());
            if(loaninfo.getPushstatus().equals(Const.PushStatus.NO)) {
                loan loanupdate = new loan();
                loanupdate.setId(loaninfo.getId());
                loanupdate.setPushstatus(Const.PushStatus.YES);
                loanmapper.updateByPrimaryKeySelective(loanupdate);
            }
        }
        return ServiceResponse.createbysuccess("已催款");
    }
    public  BigDecimal statuscheck(loan loaninfo) {
        //先检查是否已还清
        //还清将贷款记录状态改为已还款
        BigDecimal last = BigDecimalUtil.sub(loaninfo.getCrAmount().doubleValue(), loaninfo.getDeRepayed().doubleValue());
        if (last.compareTo(BigDecimal.valueOf(0))==0) {
            loanmapper.updatestatus(loaninfo.getId(), Const.recordstatusenum.PAID.getCode());
            return BigDecimal.valueOf(1);
        }else if (loaninfo.getRecordstatus().equals(Const.recordstatusenum.UNPAY.getCode())) {
            Date date = new Date();
            long sum = loaninfo.getCrDeadline().getTime() - date.getTime();
//              Long sum=date.getTime();
//              return sum;
            //若已逾期将状态改为逾期
            if (sum < 0) {
                loanmapper.updatestatus(loaninfo.getId(), Const.recordstatusenum.OVERDUE.getCode());
            }
        }
      return last;  //还没还完款且没逾期的不做处理
    }
        //若未还清但未逾期就不做任何改变
    private Recordvo assembleforRecordvo(loan loaninfo) {
        Recordvo recordvo = new Recordvo();
        recordvo.setId(loaninfo.getId());
        recordvo.setRecordno(loaninfo.getRecordNo());
        recordvo.setDebitId(loaninfo.getDebitId());
        recordvo.setCrAmount(loaninfo.getCrAmount());
        recordvo.setCrDeadline(DatetimeUtil.datetostr(loaninfo.getCrDeadline()));
        recordvo.setDeAmount(loaninfo.getDeAmount());
        recordvo.setDebitname(loaninfo.getDebitname());
        if(loaninfo.getDeKind()==Const.debitstylestatusenum.ONLINE.getCode()){
            recordvo.setDeKind(Const.debitstylestatusenum.ONLINE.getValue());
        }else if(loaninfo.getDeKind()==Const.debitstylestatusenum.OFFLINE.getCode()) {
            recordvo.setDeKind(Const.debitstylestatusenum.OFFLINE.getValue());
        }
        recordvo.setDeRepayed(loaninfo.getDeRepayed());
        if(loaninfo.getPushstatus()==Const.PushStatus.NO){
            recordvo.setPushstatus("NO");
        }else if(loaninfo.getPushstatus()==Const.PushStatus.YES){
            recordvo.setPushstatus("Yes");
        }
        if(loaninfo.getRecordstatus()==Const.recordstatusenum.UNPAY.getCode()){
            recordvo.setRecordstatus(Const.recordstatusenum.UNPAY.getValue());
        }else if(loaninfo.getRecordstatus()==Const.recordstatusenum.OVERDUE.getCode()){
            recordvo.setRecordstatus(Const.recordstatusenum.OVERDUE.getValue());
        }else if(loaninfo.getRecordstatus()==Const.recordstatusenum.PAID.getCode()) {
            recordvo.setRecordstatus(Const.recordstatusenum.PAID.getValue());
        }
        recordvo.setDeTime(DatetimeUtil.datetostr(loaninfo.getDeTime()));
        return recordvo;
    }
}
