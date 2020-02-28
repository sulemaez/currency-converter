package com.soyeyo.currencyconverter;

import com.soyeyo.currencyconverter.models.ConversionCurrency;
import com.soyeyo.currencyconverter.models.Currency;
import com.soyeyo.currencyconverter.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.properties")
public class CurrencyConverterIntegrationTest {

    @Mock
    private CurrencyRepository repository;

    private RestTemplate restTemplate;

    private String basePath = "http://localhost:8080";

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void convertShouldBeSuccessful() {
        Currency currencyEUR = new Currency("EUR", 1);
        Currency currencyUSD = new Currency("USD", 1.15795);

        Mockito.when(repository.findById("EUR")).thenReturn(Optional.of(currencyEUR));
        Mockito.when(repository.findById("USD")).thenReturn(Optional.of(currencyUSD));

        ConversionCurrency conversionCurrency = new ConversionCurrency("USD", "EUR", 10);

        ResponseEntity<Double> responseEntity = restTemplate.postForEntity(basePath + "/convert",
                conversionCurrency, Double.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

}
