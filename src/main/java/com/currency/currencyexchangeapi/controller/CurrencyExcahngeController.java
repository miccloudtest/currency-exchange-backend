package com.currency.currencyexchangeapi.controller;

import com.currency.currencyexchangeapi.entities.CurrencyResponse;
import com.currency.currencyexchangeapi.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("currency")
public class CurrencyExcahngeController {
    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @GetMapping("getcurrencydata/{date}")
    public String loadCurrencyExchangeRates(@PathVariable("date") String date) throws IOException {
        return currencyExchangeService.getCurrencyExchangeData(date);
    }

    @GetMapping("gethistoricaldata")
    public List<CurrencyResponse> getHistoricalData() throws IOException {
        return currencyExchangeService.gatHistoricalData();
    }

}
