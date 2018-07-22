package com.cryptocheck.microservices.neo.controller;

import com.cryptocheck.microservices.neo.domain.service.PriceRequest;
import com.cryptocheck.microservices.neo.domain.service.PriceResponse;
import com.cryptocheck.microservices.neo.service.IPriceService;
import com.cryptocheck.microservices.neo.service.IScheduledPriceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
public class NeoServiceController {

    @Value("${server.port}")
    private String serverPort;

    @Resource(name="NeoPriceService")
    private IPriceService<PriceResponse,PriceRequest> priceService;

    @PostMapping(value = "/getNeoPrice", consumes = "application/json", produces = "application/json")
    public CompletableFuture<PriceResponse> getNeoPrice(@RequestBody PriceRequest request) {
        return priceService.fetchPrice(request);
    }

    @GetMapping("/getNeoServiceStatus")
    public String getNeoStatus() {
        return "Neo Services running on port :"+serverPort;
    }
}
