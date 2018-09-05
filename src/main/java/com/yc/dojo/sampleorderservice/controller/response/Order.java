package com.yc.dojo.sampleorderservice.controller.response;

import java.math.BigDecimal;

public class Order {
    private final String name;
    private final Integer amount;
    private final BigDecimal totalPrice;
    
    public Order(final String name, final Integer amount, final BigDecimal totalPrice) {
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
        
        Order order = (Order) o;
        
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        return totalPrice != null ? totalPrice.equals(order.totalPrice) : order.totalPrice == null;
    }
    
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }
}
