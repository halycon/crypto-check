package com.cryptocheck.microservices.btc.cotrollers;

import com.cryptocheck.microservices.btc.domains.Coin;
import com.cryptocheck.microservices.btc.services.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinController {

    @Autowired
    BitcoinService bitcoinService;

    @GetMapping("/getBitcoin")
    public Coin getBitcoin(){
        return bitcoinService.getBitcoin();
    }

}
