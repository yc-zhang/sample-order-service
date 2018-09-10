package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.Item;
import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.ACCEPTED;

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
    public void shouldReturnGreeting() {
        OrderController controller = new OrderController();
        ResponseEntity responseEntity = controller.createOrder(orderRequest);
     
        assertThat(responseEntity.getStatusCode(), is(ACCEPTED));
        assertThat(responseEntity.getBody(), nullValue());
    }
    
    private Item createItem(String name, Integer amount) {
        Item item = new Item();
        item.setAmount(amount);
        item.setName(name);
        return item;
    }
    
    @Test
    public void shouldReturnListOrder() {
        OrderController controller = new OrderController();
        ResponseEntity responseEntity = controller.listOrder("someone");
        String body = (String) responseEntity.getBody();
        assertThat(body, is("{\"count\": 2, \"orders\": [{\"id\": 1, \"created\": \"2018\"}, {\"id\": 2, \"created\": \"2019\"} ] }"));
    }
    
    @Test
    public void shouldReturnOrderDetail() {
        OrderController controller = new OrderController();
        ResponseEntity responseEntity = controller.fetchOrderDetail("someone", 10);
        String body = (String) responseEntity.getBody();
        assertThat(body, is("{\n" +
                "    \"created\": \"2018-08-15T17:19:21.000Z\",\n" +
                "    \"orders\": [\n" +
                "        {\n" +
                "            \"amount\": 10,\n" +
                "            \"name\": \"apple\",\n" +
                "            \"totalPrice\": 10.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 22,\n" +
                "            \"name\": \"banana\",\n" +
                "            \"totalPrice\": 22.0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalPrice\": 32.0\n" +
                "}"));
    }
}
