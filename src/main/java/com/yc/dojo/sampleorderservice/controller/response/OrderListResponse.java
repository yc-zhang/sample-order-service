package com.yc.dojo.sampleorderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderListResponse {
    @JsonProperty("orders")
    private final List<OrderSummary> orderSummaries;
    private final Integer count;
    
    public OrderListResponse(final List<OrderSummary> orderSummaries, final Integer count) {
        this.orderSummaries = orderSummaries;
        this.count = count;
    }
    
    public List<OrderSummary> getOrderSummaries() {
        return orderSummaries;
    }
    
    public Integer getCount() {
        return count;
    }
}
