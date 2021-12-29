package com.entity;
import java.io.Serializable;


public class SWash implements Serializable {
    private static final long serialVersionUID = 399758980856174915L;
    
    private String type;
    
    private Long money;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

}

