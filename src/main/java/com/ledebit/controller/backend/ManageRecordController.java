package com.ledebit.controller.backend;

import com.github.pagehelper.PageInfo;
import com.ledebit.common.Const;
import com.ledebit.common.PageinfoResponse;
import com.ledebit.common.ResponseCode;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.User;
import com.ledebit.pojo.loan;
import com.ledebit.service.IRecordService;
import com.ledebit.service.IUserservice;
import com.ledebit.vo.Recordvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("backend/record")
public class ManageRecordController {
    @Autowired
    private IRecordService iRecordService;
    @Autowired
    private IUserservice iUserservice;

    // 手动添加更新记录done
    @RequestMapping(value = "input.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<Recordvo> save(HttpSession session, loan loaninfo){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServiceResponse.createbyerrorcodemessage(ResponseCode.NEED_LOGIN.getcode(), "用户未登录，请先登录再完成操作");
//        }
//        if (iUserservice.checkadminrole(user).issuccess()) {

            //对贷款人的信息进行更新或者修改
        if(1==1){
            return iRecordService.add(loaninfo);
        } else {
            return ServiceResponse.createbyerror("无操作权限");
        }
    }
    //分页列表 done
    @RequestMapping(value = "lista.do",method = RequestMethod.POST)
    @ResponseBody
    public PageinfoResponse<List> listall(HttpSession session, @RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                              @RequestParam(value = "pagesize",defaultValue = "10")int pagesize){
//        User Currentuser=(User)session.getAttribute(Const.CURRENT_USER);
//        if(Currentuser==null){
//            return PageinfoResponse.createbyerror("用户未登录");
//        }
//        //校验是否为管理员，管理员才可以进行这个操作
//        ServiceResponse response=iUserservice.checkadminrole(Currentuser);
//        if(!response.issuccess()){
        if(1==0){

            //非管理员
            return PageinfoResponse.createbyerror("无操作权限");
        }
        else{
            //是管理员
            //业务逻辑
            return iRecordService.listall(pagenum,pagesize);
        }
    }
    @RequestMapping(value = "listoverdue.do",method = RequestMethod.POST)
    @ResponseBody
    public PageinfoResponse<List> ListOverDue(HttpSession session, @RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                          @RequestParam(value = "pagesize",defaultValue = "10")int pagesize){
//        User Currentuser=(User)session.getAttribute(Const.CURRENT_USER);
//        if(Currentuser==null){
//            return PageinfoResponse.createbyerror("用户未登录");
//        }
//        //校验是否为管理员，管理员才可以进行这个操作
//        ServiceResponse response=iUserservice.checkadminrole(Currentuser);
//        if(!response.issuccess()){
        if(1==0){

            //非管理员
            return PageinfoResponse.createbyerror("无操作权限");
        }
        else{
            //是管理员
            //业务逻辑
            return iRecordService.listoverdue(pagenum,pagesize);
        }
    }
    //记录分页查询
    @RequestMapping(value = "searchre.do",method = RequestMethod.POST)
    @ResponseBody
    public PageinfoResponse<List> searchRecord(HttpSession session, @RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                             @RequestParam(value = "pagesize",defaultValue = "10")int pagesize,Integer debitId,String debitname){
//        User Currentuser=(User)session.getAttribute(Const.CURRENT_USER);
//        if(Currentuser==null){
//            return PageinfoResponse.createbyerror("用户未登录");
//        }
//        //校验是否为管理员，管理员才可以进行这个操作
//        ServiceResponse response=iUserservice.checkadminrole(Currentuser);
//        if(!response.issuccess()){
        if(1==0){
            //非管理员
            return PageinfoResponse.createbyerror("无操作权限");
        }
        else{
            //是管理员
            //业务逻辑
            //通过id或者名字（模糊查询）得到贷款记录的集合
            return iRecordService.searchRecord(pagenum,pagesize,debitId,debitname);
        }
    }
    //根据传过来的订单记录集合调整其中的状态
    @RequestMapping(value = "updatestatus.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse changestatus(HttpSession session,@RequestBody List<Recordvo> recordList){
        User Currentuser=(User)session.getAttribute(Const.CURRENT_USER);
        if(Currentuser==null){
            return ServiceResponse.createbyerror("用户未登录");
        }
        //校验是否为管理员，管理员才可以进行这个操作
        ServiceResponse response=iUserservice.checkadminrole(Currentuser);
        if(!response.issuccess()){
//        if(1==0){
            //非管理员
            return ServiceResponse.createbyerror("无操作权限");
        }
        else{
            //是管理员
            //业务逻辑
            //订单集合校验状态是否合法并修改
           return iRecordService.statuschange(recordList);

        }
    }


    @RequestMapping(value = "push.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse push(HttpSession session,@RequestBody List<Recordvo> recordList){
        User Currentuser=(User)session.getAttribute(Const.CURRENT_USER);
        if(Currentuser==null){
            return ServiceResponse.createbyerror("用户未登录");
        }
        //校验是否为管理员，管理员才可以进行这个操作
        ServiceResponse response=iUserservice.checkadminrole(Currentuser);
        if(!response.issuccess()){
            //非管理员
            return ServiceResponse.createbyerror("无操作权限");
        }
        else{
            //是管理员
            //业务逻辑
            //订单集合校验是否合法，然后对逾期且未催款的进行催款
            return iRecordService.pushforward(recordList);

        }
    }

    //todo 根据时间区域产生贷款记录的报表



}
