package com.cryptocheck.ui.adaptor;

import com.cryptocheck.ui.model.service.PriceRequest;
import com.cryptocheck.ui.model.service.PriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@FeignClient("backend-services")
public interface BackendServices {


    @PostMapping(value = "/getPrices")
    List<PriceResponse> getCoinPrices(PriceRequest priceRequest);

}
