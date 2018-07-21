package com.cryptocheck.backend.services;

import com.cryptocheck.backend.adaptor.Microservices;
import com.cryptocheck.backend.domains.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CryptoCoinServiceImpl implements CryptoCoinService {

    @Autowired
    private Microservices microservices;

    @Override
    public Flux<Coin> getCryptoCoins() {

        Mono<Coin> btc = Mono.just(microservices.getBitcoin());
        Mono<Coin> eth = Mono.just(microservices.getEthereum());
        Mono<Coin> neo = Mono.just(microservices.getNeo());

        Flux<Coin> response = Flux.concat(btc);

        return response;
    }


}
