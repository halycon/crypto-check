package com.cryptocheck.microservices.neo.service.impl;

import com.cryptocheck.microservices.neo.domain.repository.Coin;
import com.cryptocheck.microservices.neo.domain.repository.Price;
import com.cryptocheck.microservices.neo.repository.INeoH2Repository;
import com.cryptocheck.microservices.neo.service.ICallBack;
import com.cryptocheck.microservices.neo.service.IScheduledPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("NeoScheduledPriceService")
public class NeoScheduledPriceService implements IScheduledPriceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public INeoH2Repository neoH2Repository;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    @Scheduled(cron = "*/5 * * * * *")
    public void savePriceEvery5Seconds() {

        try {
            neoH2Repository.save(((ICallBack<Price>) () -> getLatestPriceFromNeoApi()).onComplete());
        } catch (Exception e) {
            logger.error("savePriceEvery3Seconds error {}",e);
        }

    }

    public Price getLatestPriceFromNeoApi() throws Exception{
        String url = "https://min-api.cryptocompare.com/data/price?fsym=NEO&tsyms=BTC,USD";
        ResponseEntity<Coin> responseHttpGet = restTemplate.exchange(url,
                HttpMethod.GET, null, Coin.class);

        if(responseHttpGet.getStatusCodeValue()!=200)
            throw new Exception("getLatestPriceFromNeoApi http response code :: "+
                    responseHttpGet.getStatusCode().getReasonPhrase());

        logger.info("responseHttpGet {} {}",responseHttpGet.getStatusCodeValue(), responseHttpGet.getBody().toString());
        Price price = new Price();
        price.setPriceBTC(responseHttpGet.getBody().getBtc());
        price.setPriceUSD(responseHttpGet.getBody().getUsd());

        return price;
    }
}
