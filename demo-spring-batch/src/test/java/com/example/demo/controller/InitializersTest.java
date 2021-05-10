package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
public abstract class InitializersTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;



    private static final MySQLContainer mysql;

    static {
        mysql = new MySQLContainer("mysql:latest");
        mysql.start();
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }

    protected String createUrlWith(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    protected TestRestTemplate getRestTemplate() {
        return this.restTemplate;
    }


}
