package com.cryptocheck.ui.model.service;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceResponse implements Serializable {
    private static final long serialVersionUID = 4664265330015817344L;
    private boolean status;
    private String errorMessage;
    private BigDecimal priceUSD;
    private BigDecimal priceBTC;
    private BigDecimal smaPrice;

    public PriceResponse(){

    }

    public PriceResponse(BigDecimal priceUSD,BigDecimal priceBTC, boolean status){
        this.priceUSD = priceUSD;
        this.priceBTC = priceBTC;
        this.status = status;
    }

    public PriceResponse(BigDecimal priceUSD,BigDecimal priceBTC, BigDecimal smaPrice, boolean status){
        this.priceUSD = priceUSD;
        this.priceBTC = priceBTC;
        this.smaPrice = smaPrice;
        this.status = status;
    }

    public BigDecimal getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(BigDecimal priceUSD) {
        this.priceUSD = priceUSD;
    }

    public BigDecimal getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(BigDecimal priceBTC) {
        this.priceBTC = priceBTC;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getSmaPrice() {
        return smaPrice;
    }

    public void setSmaPrice(BigDecimal smaPrice) {
        this.smaPrice = smaPrice;
    }
}
