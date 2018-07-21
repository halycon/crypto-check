package com.cryptocheck.backend.service;

import com.cryptocheck.backend.domain.CoinEnum;
import reactor.core.publisher.Flux;

public interface IPriceService {

    Flux<String> getPrices(CoinEnum... coinEnums);
}
