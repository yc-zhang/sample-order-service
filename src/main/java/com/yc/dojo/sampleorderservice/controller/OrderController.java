package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderItem;
import com.yc.dojo.sampleorderservice.controller.response.OrderListResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderSummary;
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
    private final static Instant FAKE_INSTANT = Instant.ofEpochSecond(1534353561L);
    
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.accepted().build();
    }
    
    @RequestMapping(value = "/{customer}/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderListResponse> getOrderList(@PathVariable String customer) {
        return ResponseEntity.ok(buildDummyOrderListResponse());
    }
    
    
    @RequestMapping(value = "/{customer}/order/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable String customer, @PathVariable Integer orderId) {
        return ResponseEntity.ok(buildDummyOrderResponse());
    }
    
    private OrderListResponse buildDummyOrderListResponse() {
        List<OrderSummary> summaries = Arrays.asList(
                new OrderSummary(1, FAKE_INSTANT),
                new OrderSummary(5, FAKE_INSTANT)
        );
        return new OrderListResponse(summaries, summaries.size());
    }
    
    private OrderDetailResponse buildDummyOrderResponse() {
        List<OrderItem> orders = Arrays.asList(
                new OrderItem("apple", 10, new BigDecimal(BigInteger.valueOf(1000), 2)),
                new OrderItem("banana", 22, new BigDecimal(BigInteger.valueOf(2200), 2))
        );
        
        BigDecimal total = orders.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        
        return new OrderDetailResponse(orders, total, FAKE_INSTANT);
    }
}
