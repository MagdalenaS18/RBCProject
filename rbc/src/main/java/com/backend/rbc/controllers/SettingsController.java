package com.backend.rbc.controllers;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.services.impl.CurrencySettingsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    @Autowired
    private CurrencySettingsServiceImpl currencySettingsServiceImpl;

    @GetMapping()
    public Settings getSettingsInfo(){
        return currencySettingsServiceImpl.getSettingsInfo();
    }

    @GetMapping("/default-currency")
    public String getDefaultCurrency() {
        return currencySettingsServiceImpl.getDefaultCurrency();
    }

    @PatchMapping("/default-currency")
    public Settings setDefaultCurrency(@RequestParam String defaultCurrency) {
        Settings settings = currencySettingsServiceImpl.setDefaultCurrency(defaultCurrency);
        return settings;
    }
    @GetMapping("/default-currency/rates")
    public Map<String, Float> fetchConversionRates(){
        return currencySettingsServiceImpl.fetchRatesForDefaultCurrency();
    }

    @GetMapping("/currency-names")
    public Set<String> getCurrencyNames(){
        return currencySettingsServiceImpl.getCurrencyNames();
    }

    @GetMapping("/date/{defaultCurrency}")
    public String getCurrencyDate(@PathVariable String defaultCurrency){
        return currencySettingsServiceImpl.getDate(defaultCurrency);
    }

}
