package com.currency.currencyexchangeapi.service;


import com.currency.currencyexchangeapi.entities.CurrencyResponse;

import java.io.IOException;
import java.util.List;

public interface CurrencyExchangeService {
    public String getCurrencyExchangeData(String date) throws IOException;
    public void loadDataToDatabase(String jsonData) throws IOException;
    public List<CurrencyResponse> gatHistoricalData() throws IOException;
}
