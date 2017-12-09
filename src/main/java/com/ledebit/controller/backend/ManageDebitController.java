package com.ledebit.controller.backend;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.Const;
import com.ledebit.common.ResponseCode;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.User;
import com.ledebit.pojo.debit;
import com.ledebit.service.IDebitService;
import com.ledebit.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/30.
 */
@Controller
@RequestMapping("/backend/debitinfo")
public class ManageDebitController {
    @Autowired
    private IUserservice iUserservice;
    @Autowired
    private IDebitService iDebitService;

    //todo 显示用户的贷款记录
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse save(HttpSession session,debit debitinfo){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //对贷款人的信息进行更新或者修改
            return iDebitService.saveorupdate(debitinfo);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping(value = "changerank.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse changerank(HttpSession session,int userid,int rank){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //对贷款人的信用级别进行升级或修改
            return iDebitService.changeorupdaterank(rank,userid);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping("/search.do")
    @ResponseBody
    public ServiceResponse<PageInfo> debitSearch(HttpSession session, String name, String tel, @RequestParam(value = "pagenum", defaultValue = "1") int pagenum, @RequestParam(value = "pagesize", defaultValue = "10") int pagesize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //动态分页查询
            return iDebitService.searchdebitbynameortelorboth(name,tel,pagenum,pagesize);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    @RequestMapping("/list.do")
    @ResponseBody
    public ServiceResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pagenum", defaultValue = "1") int pagenum, @RequestParam(value = "pagesize", defaultValue = "10") int pagesize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
        }
        if (iUserservice.checkadminrole(user).issuccess()) {
            //动态分页查询
            return iDebitService.listall(pagenum,pagesize);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }


}
