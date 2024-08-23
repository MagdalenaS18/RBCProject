package com.backend.rbc.services.impl;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.exceptions.DefaultCurrencyNotSetException;
import com.backend.rbc.exceptions.SettingsNotFoundException;
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
    private CurrencySettingsRepository settingsRepository;

    @Override
    public Settings getSettingsInfo() {
        return settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new SettingsNotFoundException());
    }

    @Override
    public String getDefaultCurrency() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new DefaultCurrencyNotSetException());
        return settings.getDefaultCurrency();
    }

    @Override
    public Settings setDefaultCurrency(String currencyName){
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new SettingsNotFoundException());
        settings.setDefaultCurrency(currencyName);
        settingsRepository.save(settings);

        return settings;
    }

    @Override
    public Set<String> getCurrencyNames() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new DefaultCurrencyNotSetException());
        String defaultCurrency = settings.getDefaultCurrency();

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
        Map<String, Object> rates = (Map<String, Object>) response.get(defaultCurrency.toLowerCase());

        return rates.keySet();
    }

    @Override
    public String getDate() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new DefaultCurrencyNotSetException());
        String defaultCurrency = settings.getDefaultCurrency();

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);

        return (String) response.get("date");
    }

    @Override
    public Map<String, Object> getCurrencies(String defaultCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);

        return response;
    }

    public Map<String, Float> fetchRatesForDefaultCurrency() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new DefaultCurrencyNotSetException());
        String defaultCurrency = settings.getDefaultCurrency().toLowerCase();

        String apiUrl = "https://latest.currency-api.pages.dev/v1/currencies/" + defaultCurrency.toLowerCase() + ".json";
        System.out.println("\t\t api: " + apiUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(apiUrl, CurrencyResponse.class);
        System.out.println("\t\t response: " + response);

        if (response.getStatusCode().is2xxSuccessful()) {
            CurrencyResponse currencyResponse = response.getBody();
            System.out.println("\t\t response: " + currencyResponse);
            Map<String, Float> rates = currencyResponse.getRatesForBaseCurrency(defaultCurrency);
            return rates;
        }

        throw new UnableToFetchCurrenciesException();
    }

}
