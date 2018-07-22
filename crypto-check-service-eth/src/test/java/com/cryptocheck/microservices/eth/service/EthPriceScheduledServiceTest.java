package com.cryptocheck.microservices.eth.service;

import com.cryptocheck.microservices.eth.domain.repository.Price;
import com.cryptocheck.microservices.eth.repository.IEthH2Repository;
import com.cryptocheck.microservices.eth.service.impl.EthScheduledPriceService;
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
public class EthPriceScheduledServiceTest {

    @Autowired
    private IEthH2Repository h2Repository;

    @Autowired
    private RestTemplate restTemplate;

    private EthScheduledPriceService ethScheduledPriceService;

    @Before
    public void init(){
        ethScheduledPriceService = new EthScheduledPriceService();
        ethScheduledPriceService.ethH2Repository = h2Repository;
        ethScheduledPriceService.restTemplate = restTemplate;

    }

    @Test
    public void getLatestPriceFromEthApi_NoParam_returnsPrice() throws Exception {

        Price price = ethScheduledPriceService.getLatestPriceFromEthApi();
        Assert.assertNotNull(" price not null ",price);
        Assert.assertTrue(" priceUsd greater than 0 ",price.getPriceUSD().doubleValue()>0);
    }


}
