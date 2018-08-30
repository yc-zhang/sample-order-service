package com.yc.dojo.sampleorderservice.controller;

import com.yc.dojo.sampleorderservice.model.Greeting;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class HelloWorldControllerTest {
    @Test
    public void shouldReturnGreeting() {
        // here again
        HelloWorldController controller = new HelloWorldController();
        ResponseEntity<Greeting> responseEntity = controller.getCollectionDetails("yc");
        
        assertThat(responseEntity.getStatusCode(), is(OK));
        assertThat(responseEntity.getBody(), is(new Greeting("hello, yc")));
    }
}
