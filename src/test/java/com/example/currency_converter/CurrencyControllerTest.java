package com.example.currency_converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.currency_converter.service.CurrencyService;

@WebMvcTest
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private CurrencyService currencyService;


    
}
