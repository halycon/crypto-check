package com.cryptocheck.microservices.eth.service.impl;

import com.cryptocheck.microservices.eth.domain.repository.Coin;
import com.cryptocheck.microservices.eth.domain.repository.Price;
import com.cryptocheck.microservices.eth.repository.IEthH2Repository;
import com.cryptocheck.microservices.eth.service.ICallBack;
import com.cryptocheck.microservices.eth.service.IScheduledPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("EthScheduledPriceService")
public class EthScheduledPriceService implements IScheduledPriceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IEthH2Repository neoH2Repository;

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
        String url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD";
        ResponseEntity<Coin> responseHttpGet = restTemplate.exchange(url,
                HttpMethod.GET, null, Coin.class);

        if(responseHttpGet.getStatusCodeValue()!=200)
            throw new Exception("getLatestPriceFromEthApi http response code :: "+
                    responseHttpGet.getStatusCode().getReasonPhrase());

        if(responseHttpGet.getBody()==null)
            throw new Exception("getLatestPriceFromEthApi body null ");
        Price price = new Price();
        price.setPriceBTC(responseHttpGet.getBody().getBtc());
        price.setPriceUSD(responseHttpGet.getBody().getUsd());

        return price;
    }
}
