package com.backend.rbc.output;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class CurrencyResponse {
    private String date;
    private Map<String, Map<String, Float>> rates = new HashMap<>();

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Map<String, Float>> getRates() {
        return rates;
    }

    public void setRates(Map<String, Map<String, Float>> rates) {
        this.rates = rates;
    }

    // Method to get rates for a specific base currency
    public Map<String, Float> getRatesForBaseCurrency(String baseCurrency) {
        return rates.get(baseCurrency.toLowerCase());
    }

    @JsonAnySetter
    public void addCurrencyRates(String key, Map<String, Float> value) {
        rates.put(key, value);
    }
}