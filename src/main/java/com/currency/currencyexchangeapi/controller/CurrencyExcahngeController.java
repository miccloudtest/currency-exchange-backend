package com.currency.currencyexchangeapi.controller;

import com.currency.currencyexchangeapi.entities.CurrencyResponse;
import com.currency.currencyexchangeapi.entities.ResponseMessage;
import com.currency.currencyexchangeapi.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("currency")
public class CurrencyExcahngeController {
    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @GetMapping("loadcurrencydata/{date}")
    public ResponseEntity<ResponseMessage> loadCurrencyExchangeRates(@PathVariable("date") String date) throws IOException {
        String resBody = currencyExchangeService.getCurrencyExchangeData(date);
        return ResponseEntity.ok(ResponseMessage.builder().status(HttpStatus.OK.value()).data(resBody).message("Currency Data Loaded Successfully!").build());
    }

    @GetMapping("gethistoricaldata")
    public List<CurrencyResponse> getHistoricalData() throws IOException {
        return currencyExchangeService.gatHistoricalData();
    }

}
