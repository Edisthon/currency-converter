package com.example.currency_converter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.model.Currency;
import com.example.currency_converter.model.ExchangeRate;
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


    @GetMapping("/convert")
    public ResponseEntity<Map<String, Object>> convertCurrency(@RequestParam String from,  @RequestParam String to, @RequestParam double amount){

        log.info("Converting "+amount+""+from+" to " +to);

        double convertedAmount = currencyService.convert(from, to, amount);

        Map<String, Object> response = Map.of(
            "from", from, 
            "to", to, 
            "amount", amount, 
            "convertedAmount", convertedAmount
        );

        return ResponseEntity.ok(response);    
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgs(IllegalArgumentException exception){
        log.warn("Invalid input here ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid input", "message", exception.getMessage()));
    }

    @PostMapping("/currencies")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        log.info("REST Request: Add currency {}", currency.getCode());
        Currency savedCurrency = currencyService.saveCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurrency);
    }

    @PostMapping("/rates")
    public ResponseEntity<ExchangeRate> addExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        log.info("REST Request: Add exchange rate from {} to {}", exchangeRate.getFromCurrency(), exchangeRate.getToCurrency());
        ExchangeRate savedRate = currencyService.saveExchangeRate(exchangeRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRate);
    }

    

    
}