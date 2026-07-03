package com.example.currency_converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.currency_converter.model.ExchangeRate;
import com.example.currency_converter.repository.CurrencyRepository;
import com.example.currency_converter.repository.ExchangeRateRepository;
import com.example.currency_converter.service.CurrencyService;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvert_SuccessfulConversionAndRounding() {

        
        String from = "USD" ;
        String to = "RWF";
        double amount = 500;
        double rate = 0.92345;

        ExchangeRate mockRate = new ExchangeRate("1", from, to, rate);
        
        when(exchangeRateRepository.findByFromCurrencyAndToCurrency(from, to)).thenReturn(Optional.of(mockRate));


        double result = currencyService.convert(from, to, amount);

        assertEquals(9.235, result);
        verify(exchangeRateRepository, times(1)).findByFromCurrencyAndToCurrency(from, to);

    }
    

}
