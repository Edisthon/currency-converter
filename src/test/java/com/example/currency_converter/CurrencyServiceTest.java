package com.example.currency_converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.currency_converter.model.Currency;
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

        assertEquals(461.725, result);
        verify(exchangeRateRepository, times(1)).findByFromCurrencyAndToCurrency(from, to);
    }

    @Test
    void testConvert_NegativeAmount_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.convert("USD", "EUR", -5.0);
        });
        assertEquals("Invalid input: Amount must be greater than zero", exception.getMessage());
    }

        @Test
    void testConvert_MissingRate_ThrowsException() {

        when(exchangeRateRepository.findByFromCurrencyAndToCurrency("USD", "JPY"))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.convert("USD", "JPY", 100.0);
        });
        
        assertTrue(exception.getMessage().contains("Exchange rate not found"));
    }


    @Test
    void testSaveCurrency_Success() {
        Currency newCurrency = new Currency(null, "CAD", "Canadian Dollar");
        when(currencyRepository.findByCode("CAD")).thenReturn(Optional.empty());
        when(currencyRepository.save(newCurrency)).thenReturn(new Currency("123", "CAD", "Canadian Dollar"));
        Currency saved = currencyService.saveCurrency(newCurrency);
        assertNotNull(saved.getId());
        assertEquals("CAD", saved.getCode());
    }
    

}
