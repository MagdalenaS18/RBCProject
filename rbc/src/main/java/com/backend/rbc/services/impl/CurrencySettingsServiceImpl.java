package com.backend.rbc.services.impl;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.exceptions.UnableToFetchCurrenciesException;
import com.backend.rbc.output.CurrencyResponse;
import com.backend.rbc.repository.CurrencySettingsRepository;
import com.backend.rbc.services.CurrencySettingsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CurrencySettingsServiceImpl implements CurrencySettingsService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencySettingsRepository settingsRepository;

//    public CurrencySettingsServiceImpl(RestTemplate restTemplate, CurrencySettingsRepository settingsRepository) {
//        this.restTemplate = restTemplate;
//        this.settingsRepository = settingsRepository;
//    }

    @Override
    public Settings getSettingsInfo() {
        return settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
    }

    @Override
    public String getDefaultCurrency() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
        return settings.getDefaultCurrency();
    }

    @Override
    public Settings setDefaultCurrency(String currencyName){
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
//        settings.setId(settings.getId());
        settings.setDefaultCurrency(currencyName);
        settingsRepository.save(settings);
        return settings;
    }

    @Override
    public Set<String> getCurrencyNames() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
        String defaultCurrency = settings.getDefaultCurrency();
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
//        Map<String, Object> currencies = getCurrencies(defaultCurrency);
        Map<String, Object> rates = (Map<String, Object>) response.get(defaultCurrency.toLowerCase());
        return rates.keySet();
    }

    @Override
    public String getDate() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
        String defaultCurrency = settings.getDefaultCurrency();
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
//        Map<String, Object> currencies = getCurrencies(defaultCurrency);
        return (String) response.get("date");
    }

    @Override
    public Map<String, Object> getCurrencies(String defaultCurrency) {
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
        return response;
    }

    public Map<String, Float> fetchRatesForDefaultCurrency() {
//        String defaultCurrency = getDefaultCurrency().toLowerCase();
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
        String defaultCurrency = settings.getDefaultCurrency().toLowerCase();

        String apiUrl = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        System.out.println("\t\t api: " + apiUrl);
        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(apiUrl, CurrencyResponse.class);
        System.out.println("\t\t response: " + response);

        if (response.getStatusCode().is2xxSuccessful()) {
            CurrencyResponse currencyResponse = response.getBody();
            System.out.println("\t\t response: " + currencyResponse);
            Map<String, Float> rates = currencyResponse.getRatesForBaseCurrency(defaultCurrency);

//            Float conversionRate = rates.get(defaultCurrency.toLowerCase());
//            if (conversionRate != null) {
//                return amount * conversionRate;
//            }
            return rates;
        }

        throw new UnableToFetchCurrenciesException();
    }


//    public float convertToDefaultCurrency(float amount, String transactionCurrency) {
//        String defaultCurrency = getDefaultCurrency().toUpperCase();
////        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
////                new RuntimeException("Default currency not set in settings"));
////        String defaultCurrency = settings.getDefaultCurrency().toUpperCase();
//        if (transactionCurrency.equals(defaultCurrency)) {
//            return amount;
//        }
//
//        String apiUrl = String.format("https://latest.currency-api.pages.dev/v1/currencies/%s.json", transactionCurrency.toLowerCase());
//        System.out.println("\t\t api: " + apiUrl);
//        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(apiUrl, CurrencyResponse.class);
//        System.out.println("\t\t response: " + response);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            CurrencyResponse currencyResponse = response.getBody();
//            System.out.println("\t\t response: " + currencyResponse);
//            Map<String, Float> rates = currencyResponse.getRatesForBaseCurrency(transactionCurrency);
//
//            Float conversionRate = rates.get(defaultCurrency.toLowerCase());
//            if (conversionRate != null) {
//                return amount * conversionRate;
//            }
//        }
//
//        throw new UnableToFetchCurrenciesException();
//    }

}
