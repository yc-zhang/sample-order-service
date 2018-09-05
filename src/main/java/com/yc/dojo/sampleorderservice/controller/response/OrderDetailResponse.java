package com.yc.dojo.sampleorderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderDetailResponse {
    @JsonProperty("orders")
    private final List<OrderItem> orderItems;
    private final BigDecimal totalPrice;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private final Instant created;
    
    public OrderDetailResponse(final List<OrderItem> orderItems, final BigDecimal totalPrice, final Instant created) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.created = created;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public Instant getCreated() {
        return created;
    }
}
