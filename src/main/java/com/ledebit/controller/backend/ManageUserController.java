package com.ledebit.controller.backend;

import com.ledebit.common.Const;
import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.User;
import com.ledebit.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/30.
 */

@Controller
@RequestMapping("backend/user")

public class ManageUserController {
    @Autowired
    private IUserservice iUserService;
    @RequestMapping(value = "managelogin.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> login(String username, String password, HttpSession session){
        ServiceResponse<User> response=iUserService.login(username,password);
        if(response.issuccess()){
            User user=response.getData();
            if(user.getRole()== Const.Role.ROLEMANGER){
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else{
                return ServiceResponse.createbyerror("不是管理员不能登录");
            }
        }
        return response;
    }
    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "loginout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> Logout(HttpSession session){
        //将当前用户删除掉
        session.removeAttribute(Const.CURRENT_USER);
        return  ServiceResponse.createbysuccessmsg("登出成功");
    }

//    @RequestMapping(value = "showrecord.do",method = RequestMethod.POST)
//    @RequestBody
//    public ServiceResponse<>


}
