package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.model.Item;
import com.yc.dojo.sampleorderservice.repository.ItemRepository;
import com.yc.dojo.sampleorderservice.support.TestJpaRepository;

import java.util.List;

public class TestItemRepository extends TestJpaRepository<Item, Integer> implements ItemRepository {
    
    private List<Item> sampleItems;
    
    public void setSampleItems(List<Item> sampleItems) {
        this.sampleItems = sampleItems;
    }
    
    @Override
    public List<Item> findByOrderId(Integer orderId) {
        return sampleItems;
    }
}
