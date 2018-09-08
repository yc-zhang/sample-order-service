package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderItem;
import com.yc.dojo.sampleorderservice.controller.response.OrderListResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderSummary;
import com.yc.dojo.sampleorderservice.model.Item;
import com.yc.dojo.sampleorderservice.model.Order;
import com.yc.dojo.sampleorderservice.repository.ItemRepository;
import com.yc.dojo.sampleorderservice.repository.OrderRepository;
import com.yc.dojo.sampleorderservice.service.OrderPersistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

@RestController
public class OrderController {
    private final static BigDecimal FAKE_TOTAL_PRICE = new BigDecimal(BigInteger.valueOf(1000), 2);

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final Consumer<OrderRequest> persistService;
    
    @Autowired
    public OrderController(OrderRepository orderRepository, ItemRepository itemRepository,
                           @Qualifier("orderPersistService") Consumer<OrderRequest> persistService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.persistService = persistService;
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        this.persistService.accept(orderRequest);
        return ResponseEntity.accepted().build();
    }
    
    @RequestMapping(value = "/{customer}/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderListResponse> getOrderList(@PathVariable String customer) {
        List<Order> orders = orderRepository.findByCustomer(customer);
        return ResponseEntity.ok(buildOrderListResponse(orders));
    }
    
    
    @RequestMapping(value = "/{customer}/order/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable String customer, @PathVariable Integer orderId) {
        Optional<Order> order = orderRepository.findByIdAndCustomer(orderId, customer);

        List<Item> items = order.map(Order::getId).map(itemRepository::findByOrderId).orElse(Collections.emptyList());
    
        Instant created = order.map(Order::getCreated).orElse(null);
        
        return ResponseEntity.ok(buildOrderResponse(items, created));
    }
    
    private OrderListResponse buildOrderListResponse(List<Order> orders) {
        List<OrderSummary> summaries = orders.stream().map(o -> new OrderSummary(o.getId(), o.getCreated())).collect(toList());
        return new OrderListResponse(summaries, summaries.size());
    }
    
    private OrderDetailResponse buildOrderResponse(List<Item> items, Instant created) {
        List<OrderItem> orders = items.stream().map(i -> new OrderItem(i.getName(), i.getAmount(), FAKE_TOTAL_PRICE)).collect(toList());
    
        BigDecimal total = orders.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        
        return new OrderDetailResponse(orders, total, created);
    }
}
