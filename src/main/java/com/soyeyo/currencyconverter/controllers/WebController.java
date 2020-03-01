package com.soyeyo.currencyconverter.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(path = "/")
    public String getIndex(){
        return "index";
    }

}
