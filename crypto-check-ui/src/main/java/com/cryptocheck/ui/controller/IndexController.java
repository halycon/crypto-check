package com.cryptocheck.ui.controller;

import com.cryptocheck.ui.model.service.PriceRequest;
import com.cryptocheck.ui.model.service.SMACriteria;
import com.cryptocheck.ui.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private PriceService priceService;

    @GetMapping({"", "/"})
    public String getIndexPage(Model model){

        PriceRequest request = new PriceRequest();
        SMACriteria smaCriteria = new SMACriteria();
        smaCriteria.setPeriod(1);
        smaCriteria.setTimeFrame(2);
        request.setSmaCriteria(smaCriteria);

        model.addAttribute("prices",priceService.getPrices(request));

        return "index";
    }

}
