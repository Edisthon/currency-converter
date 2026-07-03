package com.example.currency_converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    

}
