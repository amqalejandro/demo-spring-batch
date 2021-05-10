package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class ControllerIntegrationTest extends InitializersTest {

    @Test
    public void hello_endpoint_should_return_hello_world() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String>response =getRestTemplate()
                .exchange(createUrlWith("/hello"), HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("Hello world"));
    }

}
