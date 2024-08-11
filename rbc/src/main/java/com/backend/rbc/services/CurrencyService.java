package com.backend.rbc.services;

import com.backend.rbc.output.CurrencyResponseBean;

public interface CurrencyService {
    public CurrencyResponseBean fetchCurrenciesValuesByDefaultCurrencyName(String currencyName);
}
