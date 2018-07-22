package com.cryptocheck.ui.service;


import com.cryptocheck.ui.model.service.PriceRequest;
import com.cryptocheck.ui.model.service.PriceResponse;
import reactor.core.publisher.Flux;

public interface PriceService {
    Flux<PriceResponse> getPrices(PriceRequest request);
}
