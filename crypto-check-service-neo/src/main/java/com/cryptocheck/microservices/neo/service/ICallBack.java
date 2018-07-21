package com.cryptocheck.microservices.neo.service;

@FunctionalInterface
public interface ICallBack<K>  {
    K onComplete() throws Exception;
}
