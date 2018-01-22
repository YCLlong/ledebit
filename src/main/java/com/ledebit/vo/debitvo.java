package com.ledebit.vo;

/**
 * Created by Administrator on 2017/11/30.
 */
public class debitvo {
    private Integer id;

    private String name;

    private Integer age;

    private Long identitys;

    private String location;

    private String tel;

    private String gender;

    private String email;

    private String creditrank;

    private String debittype;

    private Integer userid;

//    private List<loan> loaninfolist;
//
//    public List<loan> getLoaninfolist() {
//        return loaninfolist;
//    }
//
//    public void setLoaninfolist(List<loan> loaninfolist) {
//        this.loaninfolist = loaninfolist;
//    }
public debitvo(Integer id, String name, Integer age, Long identitys, String location, String tel, String gender, String email, String creditrank, String debittype, Integer userid) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.identitys = identitys;
    this.location = location;
    this.tel = tel;
    this.gender = gender;
    this.email = email;
    this.creditrank = creditrank;
    this.debittype = debittype;
    this.userid = userid;
}
public debitvo(){

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
        this.name = name;
    }

    public Long getidentitys() {
        return identitys;
    }

    public void setidentitys(Long identitys) {
        this.identitys = identitys;
    }

    public Integer getUserid() {

        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
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
