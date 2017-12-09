package com.ledebit.vo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
public class Debitandrecordvo {
    private  debitvo debitvos;
    private List<Recordvo> recordvoList;

    public debitvo getDebitvos() {
        return debitvos;
    }

    public void setDebitvos(debitvo debitvos) {
        this.debitvos = debitvos;
    }

    public List<Recordvo> getRecordvoList() {
        return recordvoList;
    }

    public void setRecordvoList(List<Recordvo> recordvoList) {
        this.recordvoList = recordvoList;
    }
}
