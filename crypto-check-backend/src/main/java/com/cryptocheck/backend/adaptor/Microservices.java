package com.cryptocheck.backend.adaptor;

import com.cryptocheck.backend.domain.Coin;
import com.cryptocheck.backend.domain.service.PriceRequest;
import com.cryptocheck.backend.domain.service.PriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("gateway-zuul")
public interface Microservices {

//    @GetMapping(method = RequestMethod.GET, value = "/api/btc/getBitcoin")
//    Coin getBitcoin();
//
//    @GetMapping(method = RequestMethod.GET, value = "/api/eth/getEthereum")
//    Coin getEthereum();

    @PostMapping(value = "/api/neo/getNeoPrice", consumes = "application/json", produces = "application/json")
    PriceResponse getNeoPrice(PriceRequest priceRequest);

}
