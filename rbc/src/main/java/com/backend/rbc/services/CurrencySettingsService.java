package com.backend.rbc.services;

import com.backend.rbc.entities.Settings;

import java.util.Map;
import java.util.Set;

public interface CurrencySettingsService {
    Map<String, Object> getCurrencies(String defaultCurrency);
    Set<String> getCurrencyNames();
    String getDate();
//    Map<String, Float> getRatesForBaseCurrency(String baseCurrency);
    Map<String, Float> fetchRatesForDefaultCurrency();
//    float convertToDefaultCurrency(float amount, String transactionCurrency);
    String getDefaultCurrency();
    Settings setDefaultCurrency(String currencyName);
    Settings getSettingsInfo();
}
