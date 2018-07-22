package com.cryptocheck.microservices.neo.domain.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Coin implements Serializable {
    private static final long serialVersionUID = 5595968953198955212L;
    private BigDecimal btc;
    private BigDecimal usd;

    public BigDecimal getBtc() {
        return btc;
    }

    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return Objects.equals(btc, coin.btc) &&
                Objects.equals(usd, coin.usd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(btc, usd);
    }

    @Override
    public String toString() {
        return "Coin{" +
                "btc=" + btc +
                ", usd=" + usd +
                '}';
    }
}
