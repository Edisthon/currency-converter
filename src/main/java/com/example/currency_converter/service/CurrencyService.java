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

    public Currency saveCurrency(Currency currency) {
        log.info("Admin request: Adding new currency: {}", currency.getCode());
        
        if (currencyRepository.findByCode(currency.getCode()).isPresent()) {
            log.warn("Conflict: Currency code {} already exists", currency.getCode());
            throw new IllegalStateException("Currency code " + currency.getCode() + " already exists");
        }
        
        return currencyRepository.save(currency);
    }

    public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
        log.info("Admin request: Adding new rate from {} to {} = {}", 
                exchangeRate.getFromCurrency(), exchangeRate.getToCurrency(), exchangeRate.getRate());
        if (exchangeRate.getRate() <= 0) {
            throw new IllegalArgumentException("Invalid input: Rate must be greater than zero");
        }
        var existingRate = exchangeRateRepository.findByFromCurrencyAndToCurrency(
                exchangeRate.getFromCurrency(), exchangeRate.getToCurrency());
        
        if (existingRate.isPresent()) {
            log.warn("Conflict: Exchange rate from {} to {} already exists", 
                    exchangeRate.getFromCurrency(), exchangeRate.getToCurrency());
            throw new IllegalStateException("Exchange rate from " + exchangeRate.getFromCurrency() + 
                    " to " + exchangeRate.getToCurrency() + " already exists");
        }
        return exchangeRateRepository.save(exchangeRate);
    }

     public ExchangeRate updateExchangeRate(String from, String to, double newRate) {
        log.info("Admin request: Updating rate from {} to {} to new rate: {}", from, to, newRate);
        if (newRate <= 0) {
            throw new IllegalArgumentException("Invalid input: Rate must be greater than zero");
        }
        ExchangeRate exchangeRate = exchangeRateRepository.findByFromCurrencyAndToCurrency(from, to)
                .orElseThrow(() -> new IllegalArgumentException("Invalid input: Exchange rate not found for " + from + " to " + to));
        exchangeRate.setRate(newRate);
        return exchangeRateRepository.save(exchangeRate);
    }

    
}
