package com.cryptocheck.microservices.btc.repository;

import com.cryptocheck.microservices.btc.domain.repository.Price;
import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BtcH2RepositoryTest {

    @Autowired
    private IBtcH2Repository h2Repository;

    @Test
    public void savePrice_RandomPrice_isSuccessful(){
        Price price = new Price();
        price.setPriceBTC(BigDecimal.valueOf((Math.random()*10000)));
        price.setPriceUSD(BigDecimal.valueOf((Math.random()*10000)));
        Price priceReturn  = h2Repository.save(price);
        Assert.assertNotNull("saved object is not empty", priceReturn);
        Assert.assertEquals("priceBtc values are equal", priceReturn.getPriceBTC(),price.getPriceBTC());
        Assert.assertEquals("priceUsd values are equal", priceReturn.getPriceUSD(),price.getPriceUSD());
    }

    @Test
    public void findTopByOrderByIdDesc_NoParam_isSuccessful(){
        Price price = new Price();
        price.setPriceBTC(BigDecimal.valueOf((Math.random()*10000)));
        price.setPriceUSD(BigDecimal.valueOf((Math.random()*10000)));
        h2Repository.save(price);
        Price priceReturn = h2Repository.findTopByOrderByIdDesc();
        System.out.println(priceReturn.getPriceBTC() +" "+price.getPriceBTC());
        Assert.assertNotNull("fetch object is not empty", priceReturn);
        Assert.assertEquals("priceBtc values are equal", priceReturn.getPriceBTC(),price.getPriceBTC());
        Assert.assertEquals("priceUsd values are equal", priceReturn.getPriceUSD(),price.getPriceUSD());
    }

    @Test
    public void findDataForSMACalculation_Date1MinBackRange_isSuccessful(){
        Price price = new Price();
        price.setPriceBTC(BigDecimal.valueOf((Math.random()*10000)));
        price.setPriceUSD(BigDecimal.valueOf((Math.random()*10000)));
        h2Repository.save(price);

        Price price2 = new Price();
        price2.setPriceBTC(BigDecimal.valueOf((Math.random()*10000)));
        price2.setPriceUSD(BigDecimal.valueOf((Math.random()*10000)));
        h2Repository.save(price2);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE, -Math.abs(1));

        List<Tuple> tuples = h2Repository.findDataForSMACalculation(cal.getTime(),now);
        Assert.assertTrue("fetch object price not null", tuples.size()>0);
        for (Tuple tuple: tuples) {
            Assert.assertNotNull("fetch object price not null", tuple.get(3));
        }
    }
}
