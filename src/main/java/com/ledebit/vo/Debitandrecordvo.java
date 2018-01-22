package com.ledebit.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
public class Debitandrecordvo {
    private Integer debitid;

    private String debitname;

    private String criditrank;

    private Long recordsum;//总记录数

    private BigDecimal desum;//总金额

    private BigDecimal delaysum;//欠款总额

    private Long overduesum;//催债次数

    public Integer getDebitid() {
        return debitid;
    }

    public void setDebitid(Integer debitid) {
        this.debitid = debitid;
    }

    public String getDebitname() {
        return debitname;
    }

    public void setDebitname(String debitname) {
        this.debitname = debitname;
    }

    public String getCriditrank() {
        return criditrank;
    }

    public void setCriditrank(String criditrank) {
        this.criditrank = criditrank;
    }

    public Long getRecordsum() {
        return recordsum;
    }

    public void setRecordsum(Long recordsum) {
        this.recordsum = recordsum;
    }

    public BigDecimal getDesum() {
        return desum;
    }

    public void setDesum(BigDecimal desum) {
        this.desum = desum;
    }

    public BigDecimal getDelaysum() {
        return delaysum;
    }

    public void setDelaysum(BigDecimal delaysum) {
        this.delaysum = delaysum;
    }

    public Long getOverduesum() {
        return overduesum;
    }

    public void setOverduesum(Long overduesum) {
        this.overduesum = overduesum;
    }

}
