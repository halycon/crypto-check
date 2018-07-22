package com.cryptocheck.microservices.btc.service;

@FunctionalInterface
public interface ICallBack<K>  {
    K onComplete() throws Exception;
}
