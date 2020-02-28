package com.soyeyo.currencyconverter.controllers;


import com.soyeyo.currencyconverter.models.ConversionCurrency;
import com.soyeyo.currencyconverter.models.Currency;
import com.soyeyo.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyConverterController {

  CurrencyService currencyService;

  public CurrencyConverterController(CurrencyService currencyService){
    this.currencyService = currencyService;
  }

  @RequestMapping(method = RequestMethod.POST,value = "/convert",produces = {"application/json"})
  public ResponseEntity<Double> convert(@RequestBody ConversionCurrency conversionCurrency){
    Optional<Double> resultOptional = this.currencyService.convert(conversionCurrency);
    if(resultOptional.isPresent()) return new ResponseEntity<Double>(resultOptional.get(),HttpStatus.OK);
    return new ResponseEntity<Double>(0.0,HttpStatus.BAD_REQUEST);
  }


  @RequestMapping(method = RequestMethod.GET,value = "/currencies",produces = {"application/json"})
  public ResponseEntity<List<Currency>> getCurrency(){
    return  new ResponseEntity<>(this.currencyService.getAllCurrencies(), HttpStatus.OK);
  }

}
