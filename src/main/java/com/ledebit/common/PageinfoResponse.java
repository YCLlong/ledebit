package com.ledebit.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/16.
 */
public class PageinfoResponse<T> implements Serializable {
    private int status;
    private String msg;
    private long total;
    private T data;
    private PageinfoResponse(int status){
        this.status=status;
    }
    private PageinfoResponse(int status,long total, T data){
        this.status=status;
        this.total=total;
        this.data=data;
    }
    private PageinfoResponse(int status, String msg,long total, T data){
        this.status=status;
        this.msg=msg;
        this.total=total;
        this.data=data;
    }
    private PageinfoResponse(int status, String msg){
        this.status=status;
        this.msg=msg;
    }
    @JsonIgnore
    //保证不在json序列化结果中
    public boolean issuccess(){
        return this.status==ResponseCode.SUCCESS.getcode();
    }
    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return  msg;
    }
    public long getTotal() {return total;}
    //创建一个对象，通过一个成功的状态
    public static <T> PageinfoResponse<T> createbysuccess(){
        return new PageinfoResponse<T>(ResponseCode.SUCCESS.getcode());
    }
    //返回一个信息加成功的状态
    public static <T> PageinfoResponse<T> createbysuccessmsg(String msg){
        return  new PageinfoResponse<T>(ResponseCode.SUCCESS.getcode(),msg);
    }
    public static <T> PageinfoResponse<T> createbysuccess(long total,T data){
        return new PageinfoResponse<T>(ResponseCode.SUCCESS.getcode(),total,data);
    }
    public static <T> PageinfoResponse<T> createbysuccess(String msg,long total,T data){
        return  new PageinfoResponse<T>(ResponseCode.SUCCESS.getcode(),msg,total,data);
    }
    public static <T> PageinfoResponse<T> createbyerror(){
        return new PageinfoResponse<T>(ResponseCode.ERROR.getcode());
    }
    public static <T> PageinfoResponse<T> createbyerror(String msg){
        return new PageinfoResponse<T>(ResponseCode.ERROR.getcode(),msg);
    }
    public static <T> PageinfoResponse<T>  createbyerrorcodemessage(int errorCode,String errorMessage){
        return new PageinfoResponse<T>(errorCode,errorMessage);
    }
}
