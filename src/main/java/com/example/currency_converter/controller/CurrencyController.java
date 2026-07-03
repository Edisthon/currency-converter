package com.example.currency_converter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.model.Currency;
import com.example.currency_converter.service.CurrencyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping
@RequiredArgsConstructor

public class CurrencyController{


    public final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies(){

        log.info("Fetching all currencies");
        List<Currency> currencies = currencyService.getAllCurrencies();

        return  ResponseEntity.ok(currencies);
        
    }


    

    

    
}