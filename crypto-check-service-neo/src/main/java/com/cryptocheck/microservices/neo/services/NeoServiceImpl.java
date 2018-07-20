package com.cryptocheck.microservices.neo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeoServiceImpl implements NeoService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    @GetMapping("/getNeo")
    public String getNeo() {
        return "Neo Services running on port :"+serverPort;
    }
}
