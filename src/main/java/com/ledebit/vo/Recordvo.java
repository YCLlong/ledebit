package com.ledebit.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/5.
 */
public class Recordvo {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public BigDecimal getDeAmount() {
        return deAmount;
    }

    public void setDeAmount(BigDecimal deAmount) {
        this.deAmount = deAmount;
    }

    public BigDecimal getDeRepayed() {
        return deRepayed;
    }

    public void setDeRepayed(BigDecimal deRepayed) {
        this.deRepayed = deRepayed;
    }

    public BigDecimal getCrAmount() {

        return crAmount;
    }

    public void setCrAmount(BigDecimal crAmount) {
        this.crAmount = crAmount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String debitname;

    private String deKind;

    private String deTime;

    private BigDecimal deAmount;

    private String crDeadline;

    private BigDecimal crAmount;

    private BigDecimal deRepayed;

    private Integer recordstatus;

    private Integer pushstatus;

    public String getDebitname() {
        return debitname;
    }

    public void setDebitname(String debitname) {
        this.debitname = debitname;
    }

    public String getDeKind() {
        return deKind;
    }

    public void setDeKind(String deKind) {
        this.deKind = deKind;
    }

    public String getDeTime() {
        return deTime;
    }

    public void setDeTime(String deTime) {
        this.deTime = deTime;
    }

    public String getCrDeadline() {
        return crDeadline;
    }

    public void setCrDeadline(String crDeadline) {
        this.crDeadline = crDeadline;
    }

    public Integer getRecordstatus() {
        return recordstatus;
    }

    public void setRecordstatus(Integer recordstatus) {
        this.recordstatus = recordstatus;
    }

    public Integer getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(Integer pushstatus) {
        this.pushstatus = pushstatus;
    }
}
