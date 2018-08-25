package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.model.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public ResponseEntity<Greeting> getCollectionDetails(@PathVariable("name") String name) {
        return ResponseEntity.ok(new Greeting("hello, " + name));
    }
}
