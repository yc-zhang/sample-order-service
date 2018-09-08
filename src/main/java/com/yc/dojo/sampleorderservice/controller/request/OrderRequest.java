package com.yc.dojo.sampleorderservice.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {
    
    @NotNull
    @NotBlank
    private String customer;
    
    @NotNull
    private List<ItemRequest> items;
    
    public String getCustomer() {
        return customer;
    }
    
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public List<ItemRequest> getItems() {
        return items;
    }
    
    public void setItems(List<ItemRequest> itemRequests) {
        this.items = itemRequests;
    }
}
