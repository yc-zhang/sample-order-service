package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.Item;
import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.Order;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

public class OrderControllerTest {
    
    private OrderRequest orderRequest;
    private List<Item> items;
    
    @Before
    public void setUp() throws Exception {
        items = Arrays.asList(createItem("apple", 10), createItem("banana", 20));
    
        orderRequest = new OrderRequest();
        orderRequest.setItems(items);
        orderRequest.setCustomer("sample-customer");
    }
    
    @Test
    public void shouldCreateOrder() {
        OrderController controller = new OrderController();
        ResponseEntity responseEntity = controller.createOrder(orderRequest);
     
        assertThat(responseEntity.getStatusCode(), is(ACCEPTED));
        assertThat(responseEntity.getBody(), nullValue());
    }
    
    @Test
    public void shouldReturnOrderDetail() {
        List<Order> orders = Arrays.asList(
                new Order("apple", 10, new BigDecimal(BigInteger.valueOf(1000), 2)),
                new Order("banana", 22, new BigDecimal(BigInteger.valueOf(2200), 2))
        );
        
        Instant created = Instant.ofEpochSecond(1534353561L);
        
        OrderController controller = new OrderController();
        ResponseEntity<OrderDetailResponse> responseEntity = controller.getOrderDetail("sample-customer", 20);
    
        OrderDetailResponse orderDetailResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(orderDetailResponse.getTotalPrice(), is(new BigDecimal(BigInteger.valueOf(3200), 2)));
        assertThat(orderDetailResponse.getOrders(), is(orders));
        assertThat(orderDetailResponse.getCreated(), is(created));
    }
    
    private Item createItem(String name, Integer amount) {
        Item item = new Item();
        item.setAmount(amount);
        item.setName(name);
        return item;
    }
}
