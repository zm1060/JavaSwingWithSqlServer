package com.entity;
import java.io.Serializable;


public class SState implements Serializable {
    private static final long serialVersionUID = 167881456946275939L;
    
    private String recordid;
    
    private String status;


    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

