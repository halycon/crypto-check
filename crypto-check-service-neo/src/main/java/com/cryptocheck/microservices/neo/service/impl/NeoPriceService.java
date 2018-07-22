package com.cryptocheck.microservices.neo.service.impl;

import com.cryptocheck.microservices.neo.domain.repository.Price;
import com.cryptocheck.microservices.neo.domain.service.PriceRequest;
import com.cryptocheck.microservices.neo.domain.service.PriceResponse;
import com.cryptocheck.microservices.neo.repository.INeoH2Repository;
import com.cryptocheck.microservices.neo.service.IPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service("NeoPriceService")
public class NeoPriceService implements IPriceService<PriceResponse, PriceRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public INeoH2Repository h2Repository;

    @Override
    public CompletableFuture<PriceResponse> fetchPrice(PriceRequest request) {
        if (request != null && request.getSmaCriteria() != null) {

            CompletableFuture<PriceResponse> price = CompletableFuture.supplyAsync(() ->
            {
                Price repositoryPrice = h2Repository.findTopByOrderByIdDesc();
                if(repositoryPrice==null)
                    return new PriceResponse();
                return new PriceResponse(repositoryPrice.getPriceUSD(),repositoryPrice.getPriceBTC(),true);
            });

            CompletableFuture<PriceResponse> priceSMA = CompletableFuture.supplyAsync(() ->
                    calculateSMA(request.getSmaCriteria().getPeriod(), request.getSmaCriteria().getTimeFrame()));

            return price.thenCombineAsync(priceSMA, (s, s2) ->
                    new PriceResponse(s.getPriceUSD(), s.getPriceBTC(), s2.getSmaPrice(),
                            (s2.getSmaPrice()!=BigDecimal.ZERO ? true : false)));

        } else

            return CompletableFuture.supplyAsync(() -> {
                Price repositoryPrice = h2Repository.findTopByOrderByIdDesc();
                if(repositoryPrice==null)
                    return new PriceResponse();
                return new PriceResponse(repositoryPrice.getPriceUSD(),repositoryPrice.getPriceBTC(),true);
            });
    }

    public PriceResponse calculateSMA(int period, int timeFrame) {
        Date now = new Date();
        Date timeFramedDate = calculateTimeFramedDate(timeFrame,now);
        List<Tuple> priceList = h2Repository.findDataForSMACalculation(timeFramedDate,now);
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setSmaPrice(calculateSMAPrice(priceList,period));
        return priceResponse;
    }

    public Date calculateTimeFramedDate(int timeFrame, Date now){
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE, -Math.abs(timeFrame));
        return cal.getTime();
    }

    public BigDecimal calculateSMAPrice(List<Tuple> priceList, int period){

        if(priceList==null)
            return BigDecimal.valueOf(0);
        else{
            BigDecimal sum = BigDecimal.valueOf(0);

            int cntr = 0;

            for(int i = 0 ; i < priceList.size() ; i=i+period){
                sum = sum.add((BigDecimal) priceList.get(i).get(3));
                cntr++;
            }
            if(BigDecimal.ZERO != sum)
                sum = sum.divide(BigDecimal.valueOf(cntr),RoundingMode.HALF_UP);
            return sum;
        }
    }


}
