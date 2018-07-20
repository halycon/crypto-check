package com.cryptocheck.microservices.neo.controllers;

import com.cryptocheck.microservices.neo.domains.Coin;
import com.cryptocheck.microservices.neo.services.NeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeoController {

    @Autowired
    NeoService neoService;

    @GetMapping("/getNeo")
    public Coin getNeo(){
        return neoService.getNeo();
    }
}
