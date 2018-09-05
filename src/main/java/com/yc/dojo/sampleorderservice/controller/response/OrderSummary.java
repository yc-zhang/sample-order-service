package com.yc.dojo.sampleorderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OrderSummary {
    @JsonProperty("id")
    private final Integer orderId;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private final Instant created;
    
    public OrderSummary(final Integer orderId, final Instant created) {
        this.orderId = orderId;
        this.created = created;
    }
    
    public Integer getOrderId() {
        return orderId;
    }
    
    public Instant getCreated() {
        return created;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        OrderSummary that = (OrderSummary) o;
        
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return created != null ? created.equals(that.created) : that.created == null;
    }
    
    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
