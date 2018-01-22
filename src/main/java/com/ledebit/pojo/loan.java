package com.ledebit.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class loan {
    private Integer id;
    private Long recordNo;
    private Integer debitId;
    private String debitname;
    private Integer deKind;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date deTime;
    private BigDecimal deAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date crDeadline;
    private BigDecimal crAmount;
    private BigDecimal deRepayed;
    private Date createTime;
    private Date updateTime;
    private Integer recordstatus;
    private Integer pushstatus;
    public loan(Integer id, Long recordNo,Integer debitId, String debitname, Integer deKind, Date deTime, BigDecimal deAmount, Date crDeadline, BigDecimal crAmount, BigDecimal deRepayed, Date createTime, Date updateTime, Integer recordstatus, Integer pushstatus) {
        this.id = id;
        this.recordNo = recordNo;
        this.debitId=debitId;
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
        this.id = id;
    }

    public Long getRecordNo() {
        return recordNo;
    }
    public Integer getDebitId() {
        return debitId;
    }

    public void setDebitId(Integer debitId) {
        this.debitId = debitId;
    }

    public void setRecordNo(Long recordNo) {
        this.recordNo = recordNo;
    }

    public String getDebitname() {
        return debitname;
    }

    public void setDebitname(String debitname) {
        this.debitname = debitname == null ? null : debitname.trim();
    }

    public Integer getDeKind() {
        return deKind;
    }

    public void setDeKind(Integer deKind) {
        this.deKind = deKind;
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