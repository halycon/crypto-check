package com.cryptocheck.microservices.eth.services;

import com.cryptocheck.microservices.eth.domains.Coin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class EthereumServiceImpl implements EthereumService {

//    @Value("${server.port}")
//    private String serverPort;

    @Override
    public Coin getEthereum() {
//        return "Ethereum Services running on port :"+serverPort;
        return new Coin();
    }
}
