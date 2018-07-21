package com.cryptocheck.backend.service;

import com.cryptocheck.backend.domain.Coin;
import reactor.core.publisher.Flux;

public interface CryptoCoinService {
    Flux<Coin> getCryptoCoins();

}
