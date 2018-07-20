package com.cryptocheck.microservices.btc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinServiceImpl implements BitcoinService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    @GetMapping("/getBitcoin")
    public String getBitcoin() {
        return "Bitcoin Services running on port :"+serverPort;
    }
}
