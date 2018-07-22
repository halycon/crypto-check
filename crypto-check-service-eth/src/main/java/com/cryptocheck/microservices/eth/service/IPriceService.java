package com.cryptocheck.microservices.eth.service;

import java.util.concurrent.CompletableFuture;

public interface IPriceService<K,V> {
    CompletableFuture<K> fetchPrice(V v);
}
