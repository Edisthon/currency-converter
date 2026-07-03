package com.example.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.currency_converter.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository  extends JpaRepository<ExchangeRate, String>{
    
}
