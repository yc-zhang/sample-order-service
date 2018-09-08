package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.Item;
import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.controller.response.OrderDetailResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderListResponse;
import com.yc.dojo.sampleorderservice.controller.response.OrderSummary;
import com.yc.dojo.sampleorderservice.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

public class OrderControllerTest {
    private final static Instant FAKE_INSTANT = Instant.ofEpochSecond(1534353561L);
    private OrderRequest orderRequest;
    private List<Item> items;
    private OrderController controller;
    
    private TestOrderRepository testOrderRepository;
    private TestItemRepository testItemRepository;
    
    @Before
    public void setUp() throws Exception {
        items = Arrays.asList(createItem("apple", 10), createItem("banana", 20));
    
        orderRequest = new OrderRequest();
        orderRequest.setItems(items);
        orderRequest.setCustomer("sample-customer");
    
        testOrderRepository = new TestOrderRepository();
        testItemRepository = new TestItemRepository();
        
        controller = new OrderController(testOrderRepository, testItemRepository);
    }
    
    @Test
    public void shouldCreateOrder() {
        ResponseEntity responseEntity = controller.createOrder(orderRequest);
     
        assertThat(responseEntity.getStatusCode(), is(ACCEPTED));
        assertThat(responseEntity.getBody(), nullValue());
    }
    
    @Test
    public void shouldReturnOrderList() {
        List<Order> orders = Arrays.asList(
                createSampleOrder("sample-customer"),
                createSampleOrder("sample-customer")
        );
        
        testOrderRepository.setSampleOrders(orders);
        
        ResponseEntity<OrderListResponse> responseEntity = controller.getOrderList("sample-customer");
        
        assertThat(responseEntity.getStatusCode(), is(OK));
        
        OrderListResponse orderList = responseEntity.getBody();
        assertThat(orderList.getCount(), is(2));
        orderList.getOrderSummaries().forEach(s -> assertThat(s, is(new OrderSummary(1, FAKE_INSTANT))));
    }
    
    private Order createSampleOrder(String customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setId(1);
        order.setCreated(FAKE_INSTANT);
        return order;
    }
    
    @Test
    public void shouldReturnEmptyList() {
        testOrderRepository.setSampleOrders(emptyList());
        ResponseEntity<OrderListResponse> responseEntity = controller.getOrderList("sample-customer");
    
        assertThat(responseEntity.getStatusCode(), is(OK));
        OrderListResponse orderList = responseEntity.getBody();
        assertThat(orderList.getCount(), is(0));
    }
    
    @Test
    public void shouldReturnOrderDetail() {
        Order order = new Order();
        order.setId(1);
        order.setCustomer("sample-customer");
        order.setCreated(FAKE_INSTANT);
    
        testOrderRepository.setSampleOrder(Optional.of(order));
    
        List<com.yc.dojo.sampleorderservice.model.Item> items = Arrays.asList(
                createModelItem("apple"),
                createModelItem("banana")
        );
        
        testItemRepository.setSampleItems(items);
        
        ResponseEntity<OrderDetailResponse> responseEntity = controller.getOrderDetail("sample-customer", 1);
    
        OrderDetailResponse orderDetailResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(orderDetailResponse.getTotalPrice(), is(new BigDecimal(BigInteger.valueOf(2000), 2)));
        assertThat(orderDetailResponse.getCreated(), is(FAKE_INSTANT));
    }
    
    @Test
    public void shouldReturnIfOrderNotFound() {
        ResponseEntity<OrderDetailResponse> responseEntity = controller.getOrderDetail("sample-customer", 1);
        OrderDetailResponse orderDetailResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(orderDetailResponse.getTotalPrice(), is(new BigDecimal(BigInteger.valueOf(0), 0)));
        assertThat(orderDetailResponse.getCreated(), is(nullValue()));
        
    }
    
    private com.yc.dojo.sampleorderservice.model.Item createModelItem(String name) {
        com.yc.dojo.sampleorderservice.model.Item item = new com.yc.dojo.sampleorderservice.model.Item();
        item.setAmount(10);
        item.setName(name);
        item.setCreated(FAKE_INSTANT);
        return item;
    }
    
    private Item createItem(String name, Integer amount) {
        Item item = new Item();
        item.setAmount(amount);
        item.setName(name);
        return item;
    }
}
