package com.cryptocheck.microservices.neo.service;

import com.cryptocheck.microservices.neo.domain.repository.Price;
import com.cryptocheck.microservices.neo.repository.INeoH2Repository;
import com.cryptocheck.microservices.neo.service.impl.NeoScheduledPriceService;
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
public class NeoPriceScheduledServiceTest {

    @Autowired
    private INeoH2Repository h2Repository;

    @Autowired
    private RestTemplate restTemplate;

    private NeoScheduledPriceService neoScheduledPriceService;

    @Before
    public void init(){
        neoScheduledPriceService = new NeoScheduledPriceService();
        neoScheduledPriceService.neoH2Repository = h2Repository;
        neoScheduledPriceService.restTemplate = restTemplate;

    }

    @Test
    public void getLatestPriceFromNeoApi_NoParam_returnsPrice() throws Exception {

        Price price = neoScheduledPriceService.getLatestPriceFromNeoApi();
        Assert.assertNotNull(" price not null ",price);
        Assert.assertTrue(" priceUsd greater than 0 ",price.getPriceUSD().doubleValue()>0);
    }


}
