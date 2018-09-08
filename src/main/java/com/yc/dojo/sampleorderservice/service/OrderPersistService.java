package com.yc.dojo.sampleorderservice.service;

import com.yc.dojo.sampleorderservice.controller.request.ItemRequest;
import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import com.yc.dojo.sampleorderservice.model.Item;
import com.yc.dojo.sampleorderservice.model.Order;
import com.yc.dojo.sampleorderservice.repository.ItemRepository;
import com.yc.dojo.sampleorderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderPersistService implements Consumer<OrderRequest> {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    
    @Autowired
    public OrderPersistService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }
    
    @Override
    public void accept(OrderRequest orderRequest) {
        String name = orderRequest.getCustomer();
        Order order = new Order();
        order.setCustomer(name);
        
        orderRepository.save(order);
    
        List<Item> items = orderRequest.getItems().stream().map(i -> convert(i, order.getId())).collect(Collectors.toList());
        
        itemRepository.saveAll(items);
    }
    
    private Item convert(ItemRequest itemRequest, Integer orderId) {
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setAmount(itemRequest.getAmount());
        item.setOrderId(orderId);
        return item;
    }
}
