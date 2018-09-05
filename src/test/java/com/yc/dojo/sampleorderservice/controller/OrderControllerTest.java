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
}
