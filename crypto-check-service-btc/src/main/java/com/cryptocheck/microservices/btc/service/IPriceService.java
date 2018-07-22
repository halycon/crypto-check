package com.cryptocheck.microservices.btc.service;

import java.util.concurrent.CompletableFuture;

public interface IPriceService<K,V> {
    CompletableFuture<K> fetchPrice(V v);
}
