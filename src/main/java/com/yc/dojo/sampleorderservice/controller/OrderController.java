package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.Order;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.accepted().build();
    }
    
    @RequestMapping(value = "/{customer}/order/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable String customer, @PathVariable Integer orderId) {
        return ResponseEntity.ok(buildDummyOrderResponse());
    }
    
    private OrderDetailResponse buildDummyOrderResponse() {
        List<Order> orders = Arrays.asList(
                new Order("apple", 10, new BigDecimal(BigInteger.valueOf(1000), 2)),
                new Order("banana", 22, new BigDecimal(BigInteger.valueOf(2200), 2))
        );
        
        BigDecimal total = orders.stream()
                .map(Order::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        
        return new OrderDetailResponse(orders, total, Instant.ofEpochSecond(1534353561L));
    }
}
