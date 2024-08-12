package com.backend.rbc.controllers;

import com.backend.rbc.entities.Settings;
import com.backend.rbc.services.impl.CurrencySettingsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    @Autowired
    private CurrencySettingsServiceImpl currencySettingsServiceImpl;

    @GetMapping("/default-currency")
    public String getDefaultCurrency() {
        return currencySettingsServiceImpl.getDefaultCurrency();
    }

    @PostMapping("/default-currency")
    public Settings setDefaultCurrency(@RequestParam String defaultCurrency) {
        Settings settings = currencySettingsServiceImpl.setDefaultCurrency(defaultCurrency);
        return settings;
    }

//    @RequestMapping(value = "/sysDefaultCurrency", method = RequestMethod.GET,
//            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public CurrencyResponseBean getCurrencyValue(@RequestParam String currencyName){
//        CurrencyResponseBean currencyResponseBean = currencyService.fetchCurrenciesValuesByDefaultCurrencyName(currencyName);
//
//        return currencyResponseBean;
//
//    }

}
