package com.cryptocheck.microservices.eth.controllers;

import com.cryptocheck.microservices.eth.domains.Coin;
import com.cryptocheck.microservices.eth.services.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EthereumController {

    @Autowired
    EthereumService ethereumService;

    @GetMapping("/getEthereum")
    public Coin getEthereum(){
        return ethereumService.getEthereum();
    }
}
