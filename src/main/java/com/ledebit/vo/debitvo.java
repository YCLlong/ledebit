package com.ledebit.vo;

import com.ledebit.pojo.loan;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
public class debitvo {
    private Integer age;

    private String name;

    private String job;

    private String tel;

    private Integer income;

    private String email;

    private Integer creditrank;

    private Integer debittype;

//    private List<loan> loaninfolist;
//
//    public List<loan> getLoaninfolist() {
//        return loaninfolist;
//    }
//
//    public void setLoaninfolist(List<loan> loaninfolist) {
//        this.loaninfolist = loaninfolist;
//    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreditrank() {
        return creditrank;
    }

    public void setCreditrank(Integer creditrank) {
        this.creditrank = creditrank;
    }

    public Integer getDebittype() {
        return debittype;
    }

    public void setDebittype(Integer debittype) {
        this.debittype = debittype;
    }
}
