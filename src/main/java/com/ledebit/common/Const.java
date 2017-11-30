package com.ledebit.common;

/**
 * Created by Administrator on 2017/11/30.
 */
public class Const  {
    public static  final String CURRENT_USER="currentuser";
    public static final String EMAIL="email";
    public static final String USERNAME="username";
    public interface Role{
        int ROLECOSTOMMER = 0;//普通用户
        int ROLEMANGER =1; //管理员
    }
    public enum creditranksenum{
        ROOKIE(0,"新人"),
        COPPER(10,"铜"),
        SILIVER(20,"银"),
        GOLDEN(30,"金"),
        TOP(40,"至尊")
        ;
        private String value;
        private int code;

        creditranksenum(int code,String value){
            this.code=code;
            this.value=value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static creditranksenum codeof(int code){
            for(creditranksenum creditranksenum:values()){
                if(creditranksenum.getCode()==code){
                    return  creditranksenum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }
}
