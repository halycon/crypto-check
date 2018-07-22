package com.cryptocheck.backend.service;

import com.cryptocheck.backend.adaptor.Microservices;
import com.cryptocheck.backend.domain.service.PriceRequest;
import com.cryptocheck.backend.domain.service.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private Microservices microservices;

    @Override
    public Flux<PriceResponse> getPrices(PriceRequest request) {

//        Mono<Coin> btc = Mono.just(microservices.getBitcoin());
//        Mono<Coin> eth = Mono.just(microservices.getEthereum());

        Mono<PriceResponse> neo = Mono.just(microservices.getNeoPrice(request));

        Flux<PriceResponse> response = Flux.concat(neo,neo);

        return response;
    }


}
