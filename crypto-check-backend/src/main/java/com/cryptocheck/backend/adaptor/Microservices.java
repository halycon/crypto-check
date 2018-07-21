package com.cryptocheck.backend.adaptor;

import com.cryptocheck.backend.domains.Coin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@FeignClient("gateway-zuul")
public interface Microservices {

    @RequestMapping(method = RequestMethod.GET, value = "/api/btc/getBitcoin")
    Coin getBitcoin();

    @RequestMapping(method = RequestMethod.GET, value = "/api/eth/getEthereum")
    Coin getEthereum();

    @RequestMapping(method = RequestMethod.GET, value = "/api/neo/getNeo")
    Coin getNeo();

}