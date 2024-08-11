package com.backend.rbc.controllers;

import com.backend.rbc.output.CurrencyResponseBean;
import com.backend.rbc.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyDetailsController {
    @Autowired
    private CurrencyService currencyService;

    // @RequestMapping(value=... method=RequestMethod.GET, ...)
    @RequestMapping(value = "/sysDefaultCurrency", method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyResponseBean getCurrencyValue(@RequestParam String currencyName){
        CurrencyResponseBean currencyResponseBean = currencyService.fetchCurrenciesValuesByDefaultCurrencyName(currencyName);

        return currencyResponseBean;

    }
}
