package com.entity;

import java.io.Serializable;


public class SUser implements Serializable {
    private static final long serialVersionUID = -65775805860496890L;
    
    private String userid;
    
    private String username;
    
    private String level;
    
    private String password;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

