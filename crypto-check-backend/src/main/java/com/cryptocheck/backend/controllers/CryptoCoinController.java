package com.cryptocheck.backend.controllers;

import com.cryptocheck.backend.domains.Coin;
import com.cryptocheck.backend.services.CryptoCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CryptoCoinController {

    @Autowired
    private CryptoCoinService cryptoCoinService;


    @GetMapping("getCoins")
    public Flux<Coin> getCoins(){
        return cryptoCoinService.getCryptoCoins();
    }

}
