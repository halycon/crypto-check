package com.cryptocheck.backend.controller;

import com.cryptocheck.backend.domain.service.PriceRequest;
import com.cryptocheck.backend.domain.service.PriceResponse;
import com.cryptocheck.backend.domain.service.SMACriteria;
import com.cryptocheck.backend.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CryptoCoinController {

    @Autowired
    private PriceService cryptoCoinService;


    @PostMapping(value= "getPrices")
    public Flux<PriceResponse> getPrices(@RequestBody PriceRequest request){

        return cryptoCoinService.getPrices(request);
    }

}
