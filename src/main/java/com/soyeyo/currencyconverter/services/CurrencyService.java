package com.soyeyo.currencyconverter.services;

import com.soyeyo.currencyconverter.models.ConversionCurrency;
import com.soyeyo.currencyconverter.models.Currency;
import com.soyeyo.currencyconverter.repository.CurrencyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    public List<Currency> getAllCurrencies(){
      List<Currency> currencyList = currencyRepository.findAll();
      currencyList.sort(Comparator.comparing(Currency::getName));
      return currencyList;
    }

    public Optional<Double> convert(ConversionCurrency conversionCurrency) {
        Double res = null;
        if(conversionCurrency.getValue() == 0) return Optional.of(0.0);
        if(conversionCurrency.getValue() < 0) return Optional.of(res);

        Optional<Currency> fromOptional = currencyRepository.findById(conversionCurrency.getFrom().toUpperCase());
        Optional<Currency> toOptional = currencyRepository.findById(conversionCurrency.getTo().toUpperCase());


        if(fromOptional.isPresent() && toOptional.isPresent()){
            Double toRate = toOptional.get().getValueInUsd();
            Double fromRate = fromOptional.get().getValueInUsd();

            return Optional.of(( conversionCurrency.getValue() * toRate ) / fromRate);

        }

        return Optional.ofNullable(res);

    }
}
