package com.entity;

import java.io.Serializable;


public class SMember implements Serializable {
    private static final long serialVersionUID = -12446577781785088L;
    
    private String memberid;
    
    private String membername;
    
    private Long membermoney;


    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public Long getMembermoney() {
        return membermoney;
    }

    public void setMembermoney(Long membermoney) {
        this.membermoney = membermoney;
    }

}

