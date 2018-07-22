package com.cryptocheck.backend.controllers;

import com.cryptocheck.backend.domain.service.PriceRequest;
import com.cryptocheck.backend.domain.service.PriceResponse;
import com.cryptocheck.backend.domain.service.SMACriteria;
import com.cryptocheck.backend.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CryptoCoinController {

    @Autowired
    private PriceService cryptoCoinService;


    @GetMapping(value= "getPrices")
    public Flux<PriceResponse> getPrices(){
//        @RequestBody PriceRequest request
        PriceRequest request = new PriceRequest();
        SMACriteria a = new SMACriteria();
        a.setPeriod(1);
        a.setTimeFrame(2);
        request.setSmaCriteria(a);
        return cryptoCoinService.getPrices(request);
    }

}
