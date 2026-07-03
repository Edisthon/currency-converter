package com.example.currency_converter.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.currency_converter.model.Currency;
import com.example.currency_converter.model.ExchangeRate;
import com.example.currency_converter.repository.CurrencyRepository;
import com.example.currency_converter.repository.ExchangeRateRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    public List<Currency> getAllCurrencies(){
        log.info("Fetching all available repositories");
        return currencyRepository.findAll();
    }

    public double convert(String from, String to, double amount){
        log.info("Converting "+amount+ " "+from+ " to " +to);

        if (amount < 0){
            throw new IllegalArgumentException("Invalid input: Amount must be greater than zero");
        }

        ExchangeRate exchangeRate = exchangeRateRepository.findByFromCurrencyAndToCurrency(from, to)
            .orElseThrow(() -> new IllegalArgumentException("Exchange rate not found for "+from+" to "+to));

        
            double convertedAmount = amount *exchangeRate.getRate();

            BigDecimal bigDecimal = new BigDecimal(Double.toString(convertedAmount));
            bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);

            return bigDecimal.doubleValue();

    }
    
}
