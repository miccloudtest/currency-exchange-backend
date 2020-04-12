package com.currency.currencyexchangeapi.repository;

import com.currency.currencyexchangeapi.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {
}
