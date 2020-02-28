package com.soyeyo.currencyconverter;


import com.soyeyo.currencyconverter.models.Currency;
import com.soyeyo.currencyconverter.repository.CurrencyRepository;
import com.soyeyo.currencyconverter.services.CurrencyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {

    @Mock
    public CurrencyRepository currencyRepository;

    public CurrencyService subject;

    @Before
    public void setup(){

        subject = new CurrencyService(currencyRepository);
    }

    @Test
    public void getAllCurrenciesTest(){
        Mockito.when(currencyRepository.findAll())
                .thenReturn(
                        Arrays.asList()
                );

        List<Currency> currencies = subject.getAllCurrencies();
        Assert.assertTrue(currencies.isEmpty());
    }

    @Test
    public void getAllCurrenciesSortedTest(){
        Currency currencyOne = new Currency("KSH",100);
        Currency currencyTwo = new Currency("ABC",2);
        Currency currencyThree = new Currency("ZBC",34);
        Mockito.when(currencyRepository.findAll())
                .thenReturn(
                        Arrays.asList(currencyOne,currencyTwo,currencyThree)
                );

        List<Currency> currencies = subject.getAllCurrencies();
        Assert.assertTrue(!currencies.isEmpty());

        Assert.assertEquals(currencies.get(0),currencyTwo);
        Assert.assertEquals(currencies.get(1),currencyOne);
        Assert.assertEquals(currencies.get(2),currencyThree);
    }

    @Test(expected = NullPointerException.class)
    public void getAllCurrenciesNullTest(){

      Mockito.when(currencyRepository.findAll())
                .thenReturn(
                      null
                );

        List<Currency> currencies = subject.getAllCurrencies();

    }
}
