package com.currency.currencyexchangeapi.service;

import com.currency.currencyexchangeapi.entities.Currency;
import com.currency.currencyexchangeapi.entities.CurrencyHistoricalData;
import com.currency.currencyexchangeapi.entities.CurrencyResponse;
import com.currency.currencyexchangeapi.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public String getCurrencyExchangeData(String date) throws IOException {
        String uri = "https://api.ratesapi.io/api/";
        uri = uri + date;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        String resp = response.getBody();
        loadDataToDatabase(resp);
        return resp;
    }

    @Override
    public void loadDataToDatabase(String jsonData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(jsonData);
        CurrencyHistoricalData data = objectMapper.readValue(reader, CurrencyHistoricalData.class);
        data.getRates().entrySet().forEach(e -> {
            Currency currency = Currency.builder().currencyName(e.getKey()).rate(e.getValue()).date(data.getDate()).build();
            currencyRepository.save(currency);
        });

    }

    @Override
    public List<CurrencyResponse> gatHistoricalData() throws IOException {
        List<Currency> currencyList = currencyRepository.findAll();
        List<CurrencyResponse> currencyResponses = new ArrayList<>();
        Map<String, List<Currency>> groupByCurrencyMap = currencyList.stream().collect(Collectors.groupingBy(Currency::getCurrencyName));
        groupByCurrencyMap.entrySet().stream().forEach(e -> {
            CurrencyResponse currencyResponse = new CurrencyResponse();
            currencyResponse.setCurrency(e.getKey());
            List<Currency> currency = e.getValue();
            currency.forEach(c -> {
                String month = getMonthName(c.getDate());
                mapCurrentRateToMonth(month, currencyResponse, c.getRate());
            });
            currencyResponses.add(currencyResponse);
        });
        return currencyResponses;
    }

    private String getMonthName(Date date) {
        Format fmt = new SimpleDateFormat("MMM");
        String month = fmt.format(date);
        return month.toUpperCase();
    }

    private void mapCurrentRateToMonth(String month, CurrencyResponse currencyResponse, BigDecimal rate) {
        switch (month) {
            case "JAN":
                currencyResponse.setMonth1(String.valueOf(rate));
                break;
            case "FEB":
                currencyResponse.setMonth2(String.valueOf(rate));
                break;
            case "MAR":
                currencyResponse.setMonth3(String.valueOf(rate));
                break;
            case "APR":
                currencyResponse.setMonth4(String.valueOf(rate));
                break;
            case "MAY":
                currencyResponse.setMonth5(String.valueOf(rate));
                break;
            case "JUN":
                currencyResponse.setMonth6(String.valueOf(rate));
                break;
            case "JUL":
                currencyResponse.setMonth7(String.valueOf(rate));
                break;
            case "AUG":
                currencyResponse.setMonth8(String.valueOf(rate));
                break;
            case "SEP":
                currencyResponse.setMonth9(String.valueOf(rate));
                break;
            case "OCT":
                currencyResponse.setMonth10(String.valueOf(rate));
                break;
            case "NOV":
                currencyResponse.setMonth11(String.valueOf(rate));
                break;
            case "DEC":
                currencyResponse.setMonth12(String.valueOf(rate));
                break;
            default:
                System.out.println("Not a month!");
                break;
        }
    }
}
