package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.controller.request.OrderRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.accepted().build();
    }
    
    @RequestMapping(value = "/{user}/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listOrder(@PathVariable String user) {
        return ResponseEntity.ok("{\"count\": 2, \"orders\": [{\"id\": 1, \"created\": \"2018\"}, {\"id\": 2, \"created\": \"2019\"} ] }");
    }
    
    @RequestMapping(value = "/{user}/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchOrderDetail(@PathVariable String user, @PathVariable Integer id) {
        return ResponseEntity.ok("{\n" +
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
                "}");
    }
    
}
