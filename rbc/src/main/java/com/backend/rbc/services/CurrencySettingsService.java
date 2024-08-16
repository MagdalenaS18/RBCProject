package com.backend.rbc.services;

import com.backend.rbc.entities.Settings;

public interface CurrencySettingsService {
    float convertToDefaultCurrency(float amount, String transactionCurrency);
    String getDefaultCurrency();
    Settings setDefaultCurrency(String currencyName);
    Settings getSettingsInfo();
}
