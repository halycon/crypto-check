package com.cryptocheck.microservices.neo.domain;

import com.cryptocheck.microservices.neo.domain.repository.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class PriceEntityTest {

    @InjectMocks
    private Price price;

    @Test
    public void priceBtcScale_10_returnsWithScaledValue(){

        price.setPriceBTC(BigDecimal.valueOf(10));
        Assert.assertEquals("priceBtc equals 10.0000000000",
                BigDecimal.valueOf(10).setScale(10,BigDecimal.ROUND_HALF_UP),
                price.getPriceBTC());
    }
}
