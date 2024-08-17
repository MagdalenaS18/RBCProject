package com.backend.rbc.output;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
public class CurrencyResponse {
    private String date;
    private Map<String, Map<String, Float>> rates = new HashMap<>();

    // Method to get rates for a specific base currency
    public Map<String, Float> getRatesForBaseCurrency(String baseCurrency) {
        return rates.get(baseCurrency.toLowerCase());
    }

    @JsonAnySetter
    public void addCurrencyRates(String key, Map<String, Float> value) {
        rates.put(key, value);
    }
}