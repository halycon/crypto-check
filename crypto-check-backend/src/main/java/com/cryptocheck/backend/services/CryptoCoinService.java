package com.cryptocheck.backend.services;

import com.cryptocheck.backend.domains.Coin;
import reactor.core.publisher.Flux;

public interface CryptoCoinService {
    Flux<Coin> getCryptoCoins();

}
