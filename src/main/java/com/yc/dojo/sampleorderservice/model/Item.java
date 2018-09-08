package com.yc.dojo.sampleorderservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "items")
public class Item {
    @Id
    private Integer id;
    
    private Integer orderId;
    
    private String name;
    
    private Integer amount;
    
    @CreationTimestamp
    @Column(updatable = false)
    private Instant created;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
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
    
    public Instant getCreated() {
        return created;
    }
    
    public void setCreated(Instant created) {
        this.created = created;
    }
}
