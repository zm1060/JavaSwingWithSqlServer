package com.entity;

import java.io.Serializable;


public class SRecords implements Serializable {
    private static final long serialVersionUID = -53025437364882125L;
    
    private String recordid;
    
    private String clothingtype;
    
    private String memberid;
    
    private String washtype;
    
    private String shouldmoney;


    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getClothingtype() {
        return clothingtype;
    }

    public void setClothingtype(String clothingtype) {
        this.clothingtype = clothingtype;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getWashtype() {
        return washtype;
    }

    public void setWashtype(String washtype) {
        this.washtype = washtype;
    }

    public String getShouldmoney() {
        return shouldmoney;
    }

    public void setShouldmoney(String shouldmoney) {
        this.shouldmoney = shouldmoney;
    }

}

