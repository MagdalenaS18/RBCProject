package com.backend.rbc.controllers;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.services.CurrencySettingsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    @Autowired
    private CurrencySettingsService currencySettingsService;

    @GetMapping()
    public Settings getSettingsInfo(){
        return currencySettingsService.getSettingsInfo();
    }

    @GetMapping("/default-currency")
    public String getDefaultCurrency() {
        return currencySettingsService.getDefaultCurrency();
    }

    @PatchMapping("/default-currency")
    public Settings setDefaultCurrency(@RequestParam String defaultCurrency) {
        Settings settings = currencySettingsService.setDefaultCurrency(defaultCurrency);
        return settings;
    }

    @GetMapping("/default-currency/rates")
    public Map<String, Float> fetchConversionRates(){
        return currencySettingsService.fetchRatesForDefaultCurrency();
    }

    @GetMapping("/currency-names")
    public Set<String> getCurrencyNames(){
        return currencySettingsService.getCurrencyNames();
    }

    @GetMapping("/default-currency/date")
    public String getCurrencyDate(){
        return currencySettingsService.getDate();
    }

}
