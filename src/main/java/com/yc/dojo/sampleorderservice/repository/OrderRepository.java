package com.yc.dojo.sampleorderservice.repository;

import com.yc.dojo.sampleorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(String customer);
    
    Optional<Order> findByIdAndCustomer(Integer id, String customer);
}
