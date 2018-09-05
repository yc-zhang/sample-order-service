package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.Item;
import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderItem;
import com.yc.dojo.sampleorderservice.controller.response.OrderListResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderSummary;
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
    private final static Instant FAKE_INSTANT = Instant.ofEpochSecond(1534353561L);
    private OrderRequest orderRequest;
    private List<Item> items;
    private OrderController controller;
    
    @Before
    public void setUp() throws Exception {
        items = Arrays.asList(createItem("apple", 10), createItem("banana", 20));
    
        orderRequest = new OrderRequest();
        orderRequest.setItems(items);
        orderRequest.setCustomer("sample-customer");
    
        controller = new OrderController();
    }
    
    @Test
    public void shouldCreateOrder() {
        ResponseEntity responseEntity = controller.createOrder(orderRequest);
     
        assertThat(responseEntity.getStatusCode(), is(ACCEPTED));
        assertThat(responseEntity.getBody(), nullValue());
    }
    
    @Test
    public void shouldReturnOrderList() {
        List<OrderSummary> summaries = Arrays.asList(
                new OrderSummary(1, FAKE_INSTANT),
                new OrderSummary(5, FAKE_INSTANT)
        );
        
        ResponseEntity<OrderListResponse> responseEntity = controller.getOrderList("sample-customer");
        
        assertThat(responseEntity.getStatusCode(), is(OK));
        
        OrderListResponse orderList = responseEntity.getBody();
        assertThat(orderList.getCount(), is(2));
        assertThat(orderList.getOrderSummaries(), is(summaries));
    }
    
    @Test
    public void shouldReturnOrderDetail() {
        List<OrderItem> orders = Arrays.asList(
                new OrderItem("apple", 10, new BigDecimal(BigInteger.valueOf(1000), 2)),
                new OrderItem("banana", 22, new BigDecimal(BigInteger.valueOf(2200), 2))
        );
        
        ResponseEntity<OrderDetailResponse> responseEntity = controller.getOrderDetail("sample-customer", 20);
    
        OrderDetailResponse orderDetailResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(orderDetailResponse.getTotalPrice(), is(new BigDecimal(BigInteger.valueOf(3200), 2)));
        assertThat(orderDetailResponse.getOrderItems(), is(orders));
        assertThat(orderDetailResponse.getCreated(), is(FAKE_INSTANT));
    }
    
    private Item createItem(String name, Integer amount) {
        Item item = new Item();
        item.setAmount(amount);
        item.setName(name);
        return item;
    }
}
