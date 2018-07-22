package com.cryptocheck.ui.service;

import com.cryptocheck.ui.adaptor.BackendServices;
import com.cryptocheck.ui.model.service.PriceRequest;
import com.cryptocheck.ui.model.service.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private BackendServices backendServices;

    @Override
    public Flux<PriceResponse> getPrices(PriceRequest request) {
        Flux<PriceResponse> response = Flux.fromIterable(backendServices.getCoinPrices(request));
        return response;
    }

}
