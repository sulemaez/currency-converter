package com.soyeyo.currencyconverter.controllers;


import com.soyeyo.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyConverterController {

  @Autowired
  CurrencyService currencyService;

  @GetMapping
  public String getCurrencies(){
      return currencyService.getCurrency();
  }


}
