package com.cryptocheck.backend.service.impl;

import com.cryptocheck.backend.domain.CoinEnum;
import com.cryptocheck.backend.service.IPriceService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service("CompositePriceService")
public class CompositePriceService implements IPriceService {

    private IPriceService neoPriceService;

    private IPriceService btcPriceService;

    private IPriceService ethPriceService;


    @Override
    public Flux<String> getPrices(CoinEnum... coinEnums) {
        if(coinEnums.length==1){
            /*
                Serial price fetch
             */
            return getCoinImplementation(coinEnums[0]).getPrices(coinEnums[0]);
        }else{
            /*
                Parallel price fetch
             */
            Flux<String> ff = Flux.empty();
            for (CoinEnum coinEnum: coinEnums) {
                ff.mergeWith(getCoinImplementation(coinEnum).getPrices(coinEnum));
            }
            return ff;
        }

    }

    private IPriceService getCoinImplementation(CoinEnum coinEnum) {
        switch (coinEnum){
            case ETH:
                return ethPriceService;
            case BTC:
                return btcPriceService;
            case NEO:
                return neoPriceService;
            default:
                return null;
        }

    }
}
