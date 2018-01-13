package com.ledebit.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class debit {
    private Integer id;

    private Integer age;

    private String name;

    private String job;

    private String tel;

    private Integer gender;

    private String email;

    private Integer creditrank;

    private Integer debittype;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    public debit(Integer id, Integer age, String name, String job, String tel, Integer gender, String email, Integer creditrank, Integer debittype, Date createTime, Date updateTime, Integer userId) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.job = job;
        this.tel = tel;
        this.gender = gender;
        this.email = email;
        this.creditrank = creditrank;
        this.debittype = debittype;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public debit() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}