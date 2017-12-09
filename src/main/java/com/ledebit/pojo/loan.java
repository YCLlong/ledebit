package com.ledebit.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class loan {
    private Integer id;

    private String debitname;

    private String deKind;

    private Date deTime;

    private BigDecimal deAmount;

    private Date crDeadline;

    private BigDecimal crAmount;

    private BigDecimal deRepayed;

    private Date createTime;

    private Date updateTime;

    private Integer recordstatus;

    private Integer pushstatus;

    public loan(Integer id, String debitname, String deKind, Date deTime, BigDecimal deAmount, Date crDeadline, BigDecimal crAmount, BigDecimal deRepayed, Date createTime, Date updateTime, Integer recordstatus, Integer pushstatus) {
        this.id = id;
        this.debitname = debitname;
        this.deKind = deKind;
        this.deTime = deTime;
        this.deAmount = deAmount;
        this.crDeadline = crDeadline;
        this.crAmount = crAmount;
        this.deRepayed = deRepayed;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.recordstatus = recordstatus;
        this.pushstatus = pushstatus;
    }

    public loan() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id ;
    }

    public String getdebitname() {
        return debitname;
    }

    public void setDebitid(String debitname) {
        this.debitname = debitname ;
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

    public BigDecimal getDeAmount() {
        return deAmount;
    }

    public void setDeAmount(BigDecimal deAmount) {
        this.deAmount = deAmount;
    }

    public Date getCrDeadline() {
        return crDeadline;
    }

    public void setCrDeadline(Date crDeadline) {
        this.crDeadline = crDeadline;
    }

    public BigDecimal getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(BigDecimal crAmount) {
        this.crAmount = crAmount;
    }

    public BigDecimal getDeRepayed() {
        return deRepayed;
    }

    public void setDeRepayed(BigDecimal deRepayed) {
        this.deRepayed = deRepayed;
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

    public Integer getrecordstatus() {
        return recordstatus;
    }

    public void setrecordstatus(Integer recordstatus) {
        this.recordstatus = recordstatus;
    }

    public Integer getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(Integer pushstatus) {
        this.pushstatus = pushstatus;
    }
}