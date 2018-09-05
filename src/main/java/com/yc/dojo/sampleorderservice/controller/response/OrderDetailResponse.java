package com.yc.dojo.sampleorderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderDetailResponse {
    private final List<Order> orders;
    private final BigDecimal totalPrice;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private final Instant created;
    
    public OrderDetailResponse(final List<Order> orders, final BigDecimal totalPrice, final Instant created) {
        this.orders = orders;
        this.totalPrice = totalPrice;
        this.created = created;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public Instant getCreated() {
        return created;
    }
}
