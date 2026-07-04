package com.example.currency_converter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.example.currency_converter.model.Currency;
import com.example.currency_converter.model.ExchangeRate;
import com.example.currency_converter.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrencyService currencyService;

    @Test
    void testConvertCurrencyEndpoint_Success() throws Exception {
        when(currencyService.convert("USD", "EUR", 100.0)).thenReturn(92.000);
        mockMvc.perform(get("/convert")
                .param("from", "USD")
                .param("to", "EUR")
                .param("amount", "100.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value("USD"))
                .andExpect(jsonPath("$.to").value("EUR"))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.convertedAmount").value(92.0));
    }


     @Test
    void testConvertCurrencyEndpoint_BadRequest() throws Exception {
        when(currencyService.convert("USD", "EUR", -10.0))
                .thenThrow(new IllegalArgumentException("Amount must be greater than zero"));
        mockMvc.perform(get("/convert")
                .param("from", "USD")
                .param("to", "EUR")
                .param("amount", "-10.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid input"))
                .andExpect(jsonPath("$.message").value("Amount must be greater than zero"));
    }

    @Test
    void testAddCurrencyEndpoint_Created() throws Exception {

        Currency newCurrency = new Currency("123", "CAD", "Canadian Dollar");
        when(currencyService.saveCurrency(any(Currency.class))).thenReturn(newCurrency);

        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\":\"CAD\",\"name\":\"Canadian Dollar\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.code").value("CAD"))
                .andExpect(jsonPath("$.name").value("Canadian Dollar"));
    }

    
    @Test
    void testUpdateRateEndpoint_Success() throws Exception {
        ExchangeRate updatedRate = new ExchangeRate("1", "USD", "EUR", 0.95);
        when(currencyService.updateExchangeRate(eq("USD"), eq("EUR"), eq(0.95))).thenReturn(updatedRate);
        mockMvc.perform(put("/rates")
                .param("from", "USD")
                .param("to", "EUR")
                .param("rate", "0.95")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.fromCurrency").value("USD"))
                .andExpect(jsonPath("$.toCurrency").value("EUR"))
                .andExpect(jsonPath("$.rate").value(0.95));
    }
    
}
