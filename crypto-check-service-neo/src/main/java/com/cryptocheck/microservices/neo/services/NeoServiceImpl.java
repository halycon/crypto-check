package com.cryptocheck.microservices.neo.services;

import com.cryptocheck.microservices.neo.domains.Coin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeoServiceImpl implements NeoService {

//    @Value("${server.port}")
//    private String serverPort;

    @Override
    public Coin getNeo() {
//        return "Neo Services running on port :"+serverPort;
        return new Coin();
    }
}
