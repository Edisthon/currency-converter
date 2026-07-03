package com.example.currency_converter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;

    @Column(name = "from_currency", nullable = false, length = 3)
    private String fromCurrency;

    @Column(name = "to_currency", nullable= false, length = 3)
    private String toCurrency;

    @Column(nullable = false)
    private Double rate;
}
