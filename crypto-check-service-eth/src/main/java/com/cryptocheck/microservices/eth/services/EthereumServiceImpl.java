package com.cryptocheck.microservices.eth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EthereumServiceImpl implements EthereumService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    @GetMapping("/getEthereum")
    public String getEthereum() {
        return "Ethereum Services running on port :"+serverPort;
    }
}
