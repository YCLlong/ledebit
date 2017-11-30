package com.ledebit.pojo;

import java.util.Date;

public class loan {
    private String id;

    private String debitid;

    private String deKind;

    private Date deTime;

    private String deAmount;

    private Date crDeadline;

    private String crAmount;

    private String deRepayed;

    private Date createTime;

    private Date updateTime;

    private Integer isoverdue;

    private Integer pushstatus;

    public loan(String id, String debitid, String deKind, Date deTime, String deAmount, Date crDeadline, String crAmount, String deRepayed, Date createTime, Date updateTime, Integer isoverdue, Integer pushstatus) {
        this.id = id;
        this.debitid = debitid;
        this.deKind = deKind;
        this.deTime = deTime;
        this.deAmount = deAmount;
        this.crDeadline = crDeadline;
        this.crAmount = crAmount;
        this.deRepayed = deRepayed;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isoverdue = isoverdue;
        this.pushstatus = pushstatus;
    }

    public loan() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDebitid() {
        return debitid;
    }

    public void setDebitid(String debitid) {
        this.debitid = debitid == null ? null : debitid.trim();
    }

    public String getDeKind() {
        return deKind;
    }

    public void setDeKind(String deKind) {
        this.deKind = deKind == null ? null : deKind.trim();
    }

    public Date getDeTime() {
        return deTime;
    }

    public void setDeTime(Date deTime) {
        this.deTime = deTime;
    }

    public String getDeAmount() {
        return deAmount;
    }

    public void setDeAmount(String deAmount) {
        this.deAmount = deAmount == null ? null : deAmount.trim();
    }

    public Date getCrDeadline() {
        return crDeadline;
    }

    public void setCrDeadline(Date crDeadline) {
        this.crDeadline = crDeadline;
    }

    public String getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount == null ? null : crAmount.trim();
    }

    public String getDeRepayed() {
        return deRepayed;
    }

    public void setDeRepayed(String deRepayed) {
        this.deRepayed = deRepayed == null ? null : deRepayed.trim();
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

    public Integer getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(Integer isoverdue) {
        this.isoverdue = isoverdue;
    }

    public Integer getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(Integer pushstatus) {
        this.pushstatus = pushstatus;
    }
}