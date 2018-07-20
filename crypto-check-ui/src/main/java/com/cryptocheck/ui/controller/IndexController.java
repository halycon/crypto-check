package com.cryptocheck.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String getIndexPage(Model model){
        model.addAttribute("test","test");
        return "index";
    }

}
