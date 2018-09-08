package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.model.Order;
import com.yc.dojo.sampleorderservice.repository.OrderRepository;
import com.yc.dojo.sampleorderservice.support.TestJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestOrderRepository extends TestJpaRepository<Order, Integer> implements OrderRepository {
    
    private List<Order> sampleOrders = Collections.emptyList();
    
    private Optional<Order> sampleOrder = Optional.empty();
    
    public void setSampleOrders(List<Order> sampleOrders) {
        this.sampleOrders = sampleOrders;
    }
    
    public void setSampleOrder(Optional<Order> sampleOrder) {
        this.sampleOrder = sampleOrder;
    }
    
    @Override
    public List<Order> findByCustomer(String customer) {
        return sampleOrders;
    }
    
    @Override
    public Optional<Order> findByIdAndCustomer(Integer id, String customer) {
        return sampleOrder;
    }
}
