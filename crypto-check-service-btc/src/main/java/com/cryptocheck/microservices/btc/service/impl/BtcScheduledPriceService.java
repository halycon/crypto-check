package com.cryptocheck.microservices.btc.service.impl;

import com.cryptocheck.microservices.btc.domain.repository.Coin;
import com.cryptocheck.microservices.btc.domain.repository.Price;
import com.cryptocheck.microservices.btc.repository.IBtcH2Repository;
import com.cryptocheck.microservices.btc.service.ICallBack;
import com.cryptocheck.microservices.btc.service.IScheduledPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("BtcScheduledPriceService")
public class BtcScheduledPriceService implements IScheduledPriceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IBtcH2Repository btcH2Repository;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    @Scheduled(cron = "*/5 * * * * *")
    public void savePriceEvery5Seconds() {

        try {
            btcH2Repository.save(((ICallBack<Price>) () -> getLatestPriceFromBtcApi()).onComplete());
        } catch (Exception e) {
            logger.error("savePriceEvery3Seconds error {}",e);
        }

    }

    public Price getLatestPriceFromBtcApi() throws Exception{
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BTC,USD";
        ResponseEntity<Coin> responseHttpGet = restTemplate.exchange(url,
                HttpMethod.GET, null, Coin.class);

        if(responseHttpGet.getStatusCodeValue()!=200)
            throw new Exception("getLatestPriceFromBtcApi http response code :: "+
                    responseHttpGet.getStatusCode().getReasonPhrase());

        if(responseHttpGet.getBody()==null)
            throw new Exception("getLatestPriceFromBtcApi body null ");
        Price price = new Price();
        price.setPriceBTC(responseHttpGet.getBody().getBtc());
        price.setPriceUSD(responseHttpGet.getBody().getUsd());

        return price;
    }
}
