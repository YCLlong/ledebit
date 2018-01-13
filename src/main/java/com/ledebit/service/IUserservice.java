package com.ledebit.service;

import com.ledebit.common.ServiceResponse;
import com.ledebit.pojo.User;

/**
 * Created by Administrator on 2017/11/30.
 */
public interface IUserservice {
    ServiceResponse<User> login(String name, String password);

    ServiceResponse<User> getinfo(Integer userid);

    ServiceResponse checkadminrole(User user);

    ServiceResponse<String> register(User user);

    ServiceResponse<String> Checkvalid(String str,String type);

    ServiceResponse<String> Forgetquestion(String username);

    ServiceResponse<User> updateinfo(User user);

    ServiceResponse<String> Checkanswer(String username, String question, String answer);

    ServiceResponse<String> Resetpassword(String username, String passwordNew, String forgetToken);

    ServiceResponse<String> ResetPasswordOL(User user, String passwordnew, String passwordold);
}
