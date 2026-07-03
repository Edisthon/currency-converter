package com.example.currency_converter.service;

import org.springframework.stereotype.Service;

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
    
}
