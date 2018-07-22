package com.cryptocheck.microservices.btc.service;

import com.cryptocheck.microservices.btc.domain.repository.Price;
import com.cryptocheck.microservices.btc.repository.IBtcH2Repository;
import com.cryptocheck.microservices.btc.service.impl.BtcScheduledPriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BtcPriceScheduledServiceTest {

    @Autowired
    private IBtcH2Repository h2Repository;

    @Autowired
    private RestTemplate restTemplate;

    private BtcScheduledPriceService btcScheduledPriceService;

    @Before
    public void init(){
        btcScheduledPriceService = new BtcScheduledPriceService();
        btcScheduledPriceService.btcH2Repository = h2Repository;
        btcScheduledPriceService.restTemplate = restTemplate;

    }

    @Test
    public void getLatestPriceFromBtcApi_NoParam_returnsPrice() throws Exception {

        Price price = btcScheduledPriceService.getLatestPriceFromBtcApi();
        Assert.assertNotNull(" price not null ",price);
        Assert.assertTrue(" priceUsd greater than 0 ",price.getPriceUSD().doubleValue()>0);
    }


}
