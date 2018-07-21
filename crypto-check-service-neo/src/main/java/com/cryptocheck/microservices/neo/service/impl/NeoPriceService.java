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

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service("NeoPriceService")
public class NeoPriceService implements IPriceService<PriceResponse, PriceRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private INeoH2Repository h2Repository;

    @Override
    public CompletableFuture<PriceResponse> fetchPrice(PriceRequest request) {
        if (request != null && request.getSAMCriteria() != null) {

            CompletableFuture<PriceResponse> price = CompletableFuture.supplyAsync(() ->
            {
                Price repositoryPrice = h2Repository.findTopByOrderByIdDesc();
                return new PriceResponse(repositoryPrice.getPriceUSD(),repositoryPrice.getPriceBTC(),true);
            });
            CompletableFuture<PriceResponse> priceSMA = CompletableFuture.supplyAsync(() ->
                    calculateSMA(request.getSAMCriteria().getPeriod(), request.getSAMCriteria().getTimeFrame()));
            return price.thenCombineAsync(priceSMA, (s, s2) ->
                    new PriceResponse(s.getPriceUSD(), s.getPriceBTC(), s2.getSmaPrice(),true));

        } else

            return CompletableFuture.supplyAsync(() -> {
                Price repositoryPrice = h2Repository.findTopByOrderByIdDesc();
                logger.info("repository price :: {}",repositoryPrice.toString());
                return new PriceResponse(repositoryPrice.getPriceUSD(),repositoryPrice.getPriceBTC(),true);
            });
    }

    private PriceResponse calculateSMA(int period, int timeFrime) {


        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setSmaPrice(BigDecimal.valueOf(0));
        return priceResponse;
    }




}
