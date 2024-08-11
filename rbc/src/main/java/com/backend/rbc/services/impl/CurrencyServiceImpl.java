package com.backend.rbc.services.impl;

import com.backend.rbc.output.CurrencyDetailsBean;
import com.backend.rbc.output.CurrencyResponseBean;
import com.backend.rbc.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CurrencyResponseBean fetchCurrenciesValuesByDefaultCurrencyName(String currencyName) {
        String url = "https://latest.currency-api.pages.dev/v1/currencies/{currency}.json";
        url = url.replace("{currency}", currencyName);
        System.out.println("URL is: " + url);

        ResponseEntity<CurrencyResponseBean> currencyResponseBean = restTemplate.getForEntity(url, CurrencyResponseBean.class);

        System.out.println("Response Status Code is: "+ currencyResponseBean.getStatusCodeValue());
        CurrencyResponseBean currencyResponseBeans = currencyResponseBean.getBody();
        currencyResponseBeans.getDate();
        currencyResponseBeans.getCurrency();

        CurrencyDetailsBean currencyNames = currencyResponseBeans.getCurrency();
        System.out.println("aed: " + currencyNames.getAedCurrency()+ "\n bam: " + currencyNames.getBamCurrency()+ "\n cad: " +
                currencyNames.getCadCurrency()+ "\n chf: " +currencyNames.getChfCurrency()+ "\n rsd: " +currencyNames.getRsdCurrency()+
                "\n rub: " + currencyNames.getRubCurrency() + "\n eur: " +currencyNames.getEurCurrency() +"\n usd: "+
                currencyNames.getUsdCurrency());

        return currencyResponseBeans;
    }
}
