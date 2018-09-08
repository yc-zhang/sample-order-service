package com.yc.dojo.sampleorderservice.repository;

import com.yc.dojo.sampleorderservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOrderId(Integer orderId);
}
