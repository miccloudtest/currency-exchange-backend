package com.currency.currencyexchangeapi.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class CurrencyHistoricalData {
    private String base;
    private Map<String, BigDecimal> rates;
    private Date date;
}
