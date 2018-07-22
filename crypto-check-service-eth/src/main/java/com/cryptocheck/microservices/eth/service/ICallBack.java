package com.cryptocheck.microservices.eth.service;

@FunctionalInterface
public interface ICallBack<K>  {
    K onComplete() throws Exception;
}
