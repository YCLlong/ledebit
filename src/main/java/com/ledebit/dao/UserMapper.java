package com.ledebit.dao;

import com.ledebit.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkname(String username);

    User login(@Param("username") String username,@Param("password")  String password);

    int checkemail(String email);

    String userquestion(String username);

    int checkemailbyuserid(@Param("userid")Integer userid,@Param("email")String email);

    int resetpassword(@Param("username") String username,@Param("passwordnew") String passwordnew);

    int checkpassword(@Param("password")String password,@Param("userid")Integer userid);

    int checkanswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);
}