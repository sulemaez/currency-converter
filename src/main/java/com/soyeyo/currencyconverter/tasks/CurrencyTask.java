package com.soyeyo.currencyconverter.tasks;

import com.soyeyo.currencyconverter.models.Currency;
import com.soyeyo.currencyconverter.models.CurrencyDTO;
import com.soyeyo.currencyconverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyTask {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Value("${fixer.io.apiKey}")
  private String apiKey;

  @Scheduled( fixedRate = 5 * 1000 * 60 * 60)
  private void getRatesTask(){
      try{
          RestTemplate restTemplate = new RestTemplate();
          CurrencyDTO forObject = restTemplate.getForObject(apiKey,CurrencyDTO.class);
          forObject.getRates().forEach((k,v) ->{
              Currency currency = new Currency(k,v);
              System.out.println(currency);
              this.currencyRepository.save(currency);
          });
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
  }


}
