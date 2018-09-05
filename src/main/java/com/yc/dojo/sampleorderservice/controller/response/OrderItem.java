package com.yc.dojo.sampleorderservice.controller.response;

import java.math.BigDecimal;

public class OrderItem {
    private final String name;
    private final Integer amount;
    private final BigDecimal totalPrice;
    
    public OrderItem(final String name, final Integer amount, final BigDecimal totalPrice) {
        this.name = name;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
    
        OrderItem orderItem = (OrderItem) o;
        
        if (name != null ? !name.equals(orderItem.name) : orderItem.name != null) return false;
        if (amount != null ? !amount.equals(orderItem.amount) : orderItem.amount != null) return false;
        return totalPrice != null ? totalPrice.equals(orderItem.totalPrice) : orderItem.totalPrice == null;
    }
    
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }
}
