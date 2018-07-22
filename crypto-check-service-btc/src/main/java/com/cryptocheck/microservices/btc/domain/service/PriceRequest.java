package com.cryptocheck.microservices.btc.domain.service;

import java.io.Serializable;

public class PriceRequest implements Serializable {
    private static final long serialVersionUID = -496620387588837360L;
    private SMACriteria smaCriteria;

    public SMACriteria getSmaCriteria() {
        return smaCriteria;
    }

    public void setSmaCriteria(SMACriteria smaCriteria) {
        this.smaCriteria = smaCriteria;
    }
}
