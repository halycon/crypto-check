package com.cryptocheck.microservices.btc.services;

import com.cryptocheck.microservices.btc.domains.Coin;
import org.springframework.stereotype.Service;

//@RestController
@Service
public class BitcoinServiceImpl implements BitcoinService {

//    @Value("${server.port}")
//    private String serverPort;

    @Override
    public Coin getBitcoin() {
//        return "Bitcoin Services running on port :"+serverPort;
        return new Coin();
    }
}
