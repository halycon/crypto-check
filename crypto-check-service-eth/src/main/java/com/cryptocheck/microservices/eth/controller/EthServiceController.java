package com.cryptocheck.microservices.eth.controller;

import com.cryptocheck.microservices.eth.domain.service.PriceRequest;
import com.cryptocheck.microservices.eth.domain.service.PriceResponse;
import com.cryptocheck.microservices.eth.service.IPriceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
public class EthServiceController {

    @Value("${server.port}")
    private String serverPort;

    @Resource(name="EthPriceService")
    private IPriceService<PriceResponse,PriceRequest> priceService;

    @PostMapping(value = "/getEthPrice", consumes = "application/json", produces = "application/json")
    public CompletableFuture<PriceResponse> getNeoPrice(@RequestBody PriceRequest request) {
        return priceService.fetchPrice(request);
    }

    @GetMapping("/getEthServiceStatus")
    public String getNeoStatus() {
        return "Eth Services running on port :"+serverPort;
    }
}
