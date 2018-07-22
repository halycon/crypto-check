package com.cryptocheck.backend.service;

import com.cryptocheck.backend.domain.Coin;
import com.cryptocheck.backend.domain.service.PriceRequest;
import com.cryptocheck.backend.domain.service.PriceResponse;
import reactor.core.publisher.Flux;

public interface PriceService {
    Flux<PriceResponse> getPrices(PriceRequest request);

}
