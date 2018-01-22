package com.ledebit.controller.backend;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.Const;
import com.ledebit.common.PageinfoResponse;
import com.ledebit.common.ResponseCode;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.User;
import com.ledebit.pojo.debit;
import com.ledebit.service.IDebitService;
import com.ledebit.service.IUserservice;
import com.ledebit.vo.Debitandrecordvo;
import com.ledebit.vo.debitvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@Controller
@RequestMapping("backend/debitinfo")
public class ManageDebitController {
    @Autowired
    private IUserservice iUserservice;
    @Autowired
    private IDebitService iDebitService;

    //todo 显示用户的贷款记录
    @RequestMapping(value = "saved.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse save(HttpSession session,debit debitinfo)throws Exception{
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //对贷款人的信息进行更新或者修改
        //测试用
//        if(1==1){
            return iDebitService.saveorupdate(debitinfo);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping(value = "changerank.do",method = RequestMethod.POST)
    @ResponseBody
    //jsong格式
    public ServiceResponse changerank(HttpSession session,@RequestBody debit debits){
    //text格式
//    public ServiceResponse changerank(HttpSession session,Integer creditrank,String name){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
//        }
//        if (iUserservice.checkadminrole(user).issuccess()) {
            //对贷款人的信用级别进行升级或修改\

        //测试用
        if(1==1){
            return iDebitService.changeorupdaterank(debits.getCreditrank(),debits.getName());
//            return iDebitService.changeorupdaterank(creditrank,name);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping(value = "search.do",method = RequestMethod.POST)
    @ResponseBody
    public PageinfoResponse<List> debitSearch(HttpSession session, String name, String tel, @RequestParam(value = "pagenum", defaultValue = "1") int pagenum, @RequestParam(value = "pagesize", defaultValue = "10") int pagesize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return PageinfoResponse.createbyerror().createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
//            //动态分页查询
//        if(1==1){
            return iDebitService.searchdebitbynameortelorboth(name,tel,pagenum,pagesize);
        } else {
            return PageinfoResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping(value = "list.do",method = RequestMethod.POST)
    @ResponseBody
    public PageinfoResponse<List> list(HttpSession session, @RequestParam(value = "pagenum", defaultValue = "1") int pagenum, @RequestParam(value = "pagesize", defaultValue = "10") int pagesize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return PageinfoResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
//        if (1==1){
            //动态分页查询
            return iDebitService.listall(pagenum,pagesize);
        } else {
            return PageinfoResponse.createbyerror("无操作权限");
        }
    }

    @RequestMapping(value = "delete.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> list(HttpSession session,Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //根据id删除贷款人信息
        //测试用
//        if(1==1){
            return iDebitService.deletedebit(id);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    //todo 个人账单 包括个人姓名，身份证，信用额度（分线上线下再说）统计和debitid相关的账单数，总借款数，欠款数，逾期次数，

    @RequestMapping(value = "personalFrom.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<Debitandrecordvo> CreatePersonalForm(HttpSession session, Integer id) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
//        }
//        if (iUserservice.checkadminrole(user).issuccess()) {
            //根据id删除贷款人信息
            //测试用
        if(1==1){
            return iDebitService.personalfrom(id);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
}
