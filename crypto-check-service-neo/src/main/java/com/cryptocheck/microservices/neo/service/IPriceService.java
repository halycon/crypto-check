package com.cryptocheck.microservices.neo.service;

import java.util.concurrent.CompletableFuture;

public interface IPriceService<K,V> {
    CompletableFuture<K> fetchPrice(V v);
}
