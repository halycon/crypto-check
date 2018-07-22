package com.cryptocheck.microservices.btc.controller;

import com.cryptocheck.microservices.btc.domain.service.PriceRequest;
import com.cryptocheck.microservices.btc.domain.service.PriceResponse;
import com.cryptocheck.microservices.btc.service.IPriceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
public class BtcServiceController {

    @Value("${server.port}")
    private String serverPort;

    @Resource(name="BtcPriceService")
    private IPriceService<PriceResponse,PriceRequest> priceService;

    @PostMapping(value = "/getBtcPrice", consumes = "application/json", produces = "application/json")
    public CompletableFuture<PriceResponse> getBtcPrice(@RequestBody PriceRequest request) {
        return priceService.fetchPrice(request);
    }

    @GetMapping("/getBtcServiceStatus")
    public String getBtcStatus() {
        return "Btc Services running on port :"+serverPort;
    }
}
