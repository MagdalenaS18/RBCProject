package com.backend.rbc.services.impl;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.exceptions.UnableToFetchCurrenciesException;
import com.backend.rbc.output.CurrencyResponse;
import com.backend.rbc.repository.CurrencySettingsRepository;
import com.backend.rbc.services.CurrencySettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencySettingsServiceImpl implements CurrencySettingsService {
    private final RestTemplate restTemplate;
    private final CurrencySettingsRepository settingsRepository;

    public CurrencySettingsServiceImpl(RestTemplate restTemplate, CurrencySettingsRepository settingsRepository) {
        this.restTemplate = restTemplate;
        this.settingsRepository = settingsRepository;
    }

    @Override
    public Settings getSettingsInfo() {
        return settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
    }

    public float convertToDefaultCurrency(float amount, String transactionCurrency) {
        String defaultCurrency = getDefaultCurrency().toUpperCase();
//        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
//                new RuntimeException("Default currency not set in settings"));
//        String defaultCurrency = settings.getDefaultCurrency().toUpperCase();
        if (transactionCurrency.equals(defaultCurrency)) {
            return amount;
        }

        String apiUrl = String.format("https://latest.currency-api.pages.dev/v1/currencies/%s.json", transactionCurrency.toLowerCase());
        System.out.println("\t\t api: " + apiUrl);
        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(apiUrl, CurrencyResponse.class);
        System.out.println("\t\t response: " + response);

        if (response.getStatusCode().is2xxSuccessful()) {
            CurrencyResponse currencyResponse = response.getBody();
            System.out.println("\t\t response: " + currencyResponse);
            Map<String, Float> rates = currencyResponse.getRatesForBaseCurrency(transactionCurrency);

            Float conversionRate = rates.get(defaultCurrency.toLowerCase());
            if (conversionRate != null) {
                return amount * conversionRate;
            }
        }

        throw new UnableToFetchCurrenciesException();
    }
    // da dobijem i datum

    public String getDefaultCurrency() {
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
        return settings.getDefaultCurrency();
    }

    public Settings setDefaultCurrency(String currencyName){
        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
                new RuntimeException("Default currency not set in settings"));
//        settings.setId(settings.getId());
        settings.setDefaultCurrency(currencyName);
        settingsRepository.save(settings);
        return settings;
    }


}
