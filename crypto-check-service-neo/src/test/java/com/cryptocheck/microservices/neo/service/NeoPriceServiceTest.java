package com.cryptocheck.microservices.neo.service;

import com.cryptocheck.microservices.neo.domain.repository.Price;
import com.cryptocheck.microservices.neo.domain.service.PriceRequest;
import com.cryptocheck.microservices.neo.domain.service.PriceResponse;
import com.cryptocheck.microservices.neo.domain.service.SMACriteria;
import com.cryptocheck.microservices.neo.repository.INeoH2Repository;
import com.cryptocheck.microservices.neo.service.impl.NeoPriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeoPriceServiceTest {

    private NeoPriceService priceService;

    @Autowired
    private INeoH2Repository h2Repository;

    @Before
    public void init(){

        priceService = new NeoPriceService();
        priceService.h2Repository = h2Repository;

        Price priceRandom1 = new Price();
        priceRandom1.setPriceBTC(BigDecimal.valueOf(10000));
        priceRandom1.setPriceUSD(BigDecimal.valueOf(20000));
        h2Repository.save(priceRandom1);

        Price priceRandom2 = new Price();
        priceRandom2.setPriceBTC(BigDecimal.valueOf(10000));
        priceRandom2.setPriceUSD(BigDecimal.valueOf(10000));
        h2Repository.save(priceRandom2);
    }

    @Test
    public void calculateSMAPrice_Tuples_Period2_is20000(){

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE, -Math.abs(1));
        List<Tuple> tuples = h2Repository.findDataForSMACalculation(cal.getTime(),now);
        Assert.assertEquals(" equals 20000 ",BigDecimal.valueOf(20000),priceService.calculateSMAPrice(tuples,1));
    }

    @Test
    public void calculateTimeFramedDate_1_Now_is1MinBack(){

        Date now = new Date();
        int minuteDiff = (int)((now.getTime()/60000) - (priceService.calculateTimeFramedDate(1,now).getTime()/60000));
        Assert.assertEquals(" equals 1 minute ",1,minuteDiff);
    }

    @Test
    public void calculateSMA_1_1__is20000(){

        PriceResponse response = priceService.calculateSMA(1,5);
        Assert.assertNotNull(" PriceResponse not null ",priceService.calculateSMA(1,5));
        Assert.assertEquals(" equals 20000 ",BigDecimal.valueOf(20000),priceService.calculateSMA(1,1).getSmaPrice());
    }

    @Test
    public void fetchPrice_NoParam_returnsPriceResponse() {

        CompletableFuture<PriceResponse> priceResponse=  priceService.fetchPrice(null);
        priceResponse.whenComplete((result, ex) -> {
            Assert.assertNotNull(" PriceResponse not null ",result);
            Assert.assertTrue(" greater than 0 ",result.getPriceUSD().doubleValue()>0.0);
        });

    }

    @Test
    public void fetchPrice_SMARequest_returnsPriceResponse() {
        PriceRequest request = new PriceRequest();

        request.setSmaCriteria(new SMACriteria(1,1));

        CompletableFuture<PriceResponse> priceResponse=  priceService.fetchPrice(request);
        priceResponse.whenComplete((result, ex) -> {
            Assert.assertNotNull(" PriceResponse not null ",result);
            Assert.assertTrue(" priceUsd greater than 0 ",result.getPriceUSD().doubleValue()>0.0);
            Assert.assertTrue(" smaPrice greater than 0 ",result.getSmaPrice().doubleValue()>0.0);
        });

    }
}
