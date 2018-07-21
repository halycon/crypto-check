package com.cryptocheck.microservices.neo.domain.service;

import java.io.Serializable;

public class PriceRequest implements Serializable {
    private static final long serialVersionUID = -496620387588837360L;
    private SAMCriteria SAMCriteria;

    public SAMCriteria getSAMCriteria() {
        return SAMCriteria;
    }

    public void setSAMCriteria(SAMCriteria SAMCriteria) {
        this.SAMCriteria = SAMCriteria;
    }
}
