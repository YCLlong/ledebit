package com.ledebit.vo;

/**
 * Created by Administrator on 2017/11/30.
 */
public class debitvo {
    private Integer id;


    private Integer age;

    private String name;

    private String job;

    private String tel;

    private String gender;

    private String email;

    private String creditrank;

    private String debittype;

//    private List<loan> loaninfolist;
//
//    public List<loan> getLoaninfolist() {
//        return loaninfolist;
//    }
//
//    public void setLoaninfolist(List<loan> loaninfolist) {
//        this.loaninfolist = loaninfolist;
//    }
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditrank() {
        return creditrank;
    }

    public void setCreditrank(String creditrank) {
        this.creditrank = creditrank;
    }

    public String getDebittype() {
        return debittype;
    }

    public void setDebittype(String debittype) {
        this.debittype = debittype;
    }
}
