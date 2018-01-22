package com.ledebit.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/12/5.
 */
public class Recordvo {
    private Integer id;

    private Long recordno;


    private Integer debitId;

    public Integer getDebitId() {
        return debitId;
    }

    public void setDebitId(Integer debitId) {
        this.debitId = debitId;
    }

    private String debitname;

    private String deKind;

    private String deTime;

    private BigDecimal deAmount;

    private String crDeadline;

    private BigDecimal crAmount;

    private BigDecimal deRepayed;

    private String recordstatus;

    private String pushstatus;


    public Integer getId() {
        return id;
    }

    public String getDebitname() {
        return debitname;
    }

    public Long getRecordno() {
        return recordno;
    }

    public void setRecordno(Long recordno) {
        this.recordno = recordno;
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

    public String getRecordstatus() {
        return recordstatus;
    }

    public void setRecordstatus(String recordstatus) {
        this.recordstatus = recordstatus;
    }

    public String getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(String pushstatus) {
        this.pushstatus = pushstatus;
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
}
