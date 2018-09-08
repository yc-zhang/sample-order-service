package com.yc.dojo.sampleorderservice.controller.request;

import javax.validation.constraints.NotNull;

public class ItemRequest {
    @NotNull
    private String name;
    
    @NotNull
    private Integer amount;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
