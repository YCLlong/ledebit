package com.ledebit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ledebit.common.Const;
import com.ledebit.common.ServiceResponse;
import com.ledebit.dao.debitMapper;
import com.ledebit.pojo.loan;
import com.ledebit.service.IRecordService;
import com.ledebit.util.BigDecimalUtil;
import com.ledebit.util.DatetimeUtil;
import com.ledebit.vo.Recordvo;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ledebit.dao.loanMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public ServiceResponse<PageInfo> listall(int pagenum, int pagesize){
        PageHelper.startPage(pagenum,pagesize);
        List<loan> recordList=loanmapper.selectall();
        List<Recordvo> recordvoList= Lists.newArrayList();
        for(loan loans:recordList){
            Recordvo recordvo=assembleforRecordvo(loans);
            recordvoList.add(recordvo);
        }
        PageInfo pageresult=new PageInfo(recordList);
        pageresult.setList(recordvoList);
        return ServiceResponse.createbysuccess(pageresult);
    }

    public ServiceResponse<Recordvo> add(loan loaninfo){
        if(loaninfo==null){
            return  ServiceResponse.createbyerror("参数为空");
        }
        if(loaninfo.getId()==null) {
            //新增记录为未付款状态的，后面可手动检索调整状态
            loaninfo.setrecordstatus(Const.recordstatusenum.UNPAY.getCode());
            loaninfo.setPushstatus(Const.PushStatus.NO);
            loan loaninsert = loaninfo;
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

    public ServiceResponse<PageInfo> searchRecord(int pagenum,int pagesize,Integer loanid,String debitname){
              PageHelper.startPage(pagenum,pagesize);
        if(StringUtils.isNotBlank(debitname)){
            debitname =new StringBuilder().append("%").append(debitname).append("%").toString();
        }
        List<loan> loanList=loanmapper.selectByLoanidandDebitname(debitname,loanid);
        List<Recordvo> recordvoList=Lists.newArrayList();
        for(loan loaninfo:loanList){
            Recordvo recordvo=assembleforRecordvo(loaninfo);
            recordvoList.add(recordvo);
        }
        PageInfo pageresult=new PageInfo(loanList);
        pageresult.setList(loanList);
        return ServiceResponse.createbysuccess(pageresult);
    }
    public ServiceResponse statuschange(List<Recordvo> recordvoList){
        if(recordvoList==null){
            return ServiceResponse.createbyerror("没检测到记录，参数为空");
        }
//        List<loan> loanList=Lists.newArrayList();
        for(Recordvo recordvo:recordvoList){
            loan loaninfo=loanmapper.selectByPrimaryKey(recordvo.getId());
            statuscheck(loaninfo);
        }
        //todo 考虑是否需要返回传进列表更新之后的数据
       return ServiceResponse.createbysuccessmsg("已校验状态完成，可在查看具体信息中确认");
    }

    public ServiceResponse pushforward(List<Recordvo> recordvoList){
        if(recordvoList==null){
            return ServiceResponse.createbyerror("未检测到记录，参数为空");
        }
        for(Recordvo recordvo:recordvoList){
            loan loaninfo=loanmapper.selectByPrimaryKey(recordvo.getId());
            if(loaninfo.getPushstatus().equals(Const.PushStatus.NO)) {
                loan loanupdate = new loan();
                loanupdate.setId(loaninfo.getId());
                loanupdate.setPushstatus(Const.PushStatus.YES);
                loanmapper.updateByPrimaryKeySelective(loanupdate);
            }
        }
        return ServiceResponse.createbyerror();
    }
    private void statuscheck(loan loaninfo) {
        //先检查是否已还清
        //还清将贷款记录状态改为已还款
        BigDecimal last = BigDecimalUtil.sub(loaninfo.getCrAmount().doubleValue(), loaninfo.getDeRepayed().doubleValue());
        if (last.compareTo(BigDecimal.ZERO) == 0) {
            loanmapper.updatestatus(loaninfo.getId(), Const.recordstatusenum.PAID.getCode());
        } else if (loaninfo.getrecordstatus().equals(Const.recordstatusenum.UNPAY)) {
            Date date = new Date();
            long sum = loaninfo.getCrDeadline().getTime() - date.getTime();
            //若已逾期将状态改为逾期
            if (sum < 0) {
                loanmapper.updatestatus(loaninfo.getId(), Const.recordstatusenum.OVERDUE.getCode());
            }
        }
        //还没还完款且没逾期的不做处理
    }
        //若未还清但未逾期就不做任何改变
    private Recordvo assembleforRecordvo(loan loaninfo) {
        Recordvo recordvo = new Recordvo();
        recordvo.setId(loaninfo.getId());
        recordvo.setCrAmount(loaninfo.getCrAmount());
        recordvo.setCrDeadline(DatetimeUtil.datetostr(loaninfo.getCrDeadline()));
        recordvo.setDeAmount(loaninfo.getDeAmount());
        recordvo.setDebitname(loaninfo.getdebitname());
        recordvo.setDeKind(loaninfo.getDeKind());
        recordvo.setDeRepayed(loaninfo.getDeRepayed());
        recordvo.setPushstatus(loaninfo.getPushstatus());
        recordvo.setRecordstatus(loaninfo.getrecordstatus());
        recordvo.setDeTime(DatetimeUtil.datetostr(loaninfo.getDeTime()));
        return recordvo;
    }
}
