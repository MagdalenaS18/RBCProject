package com.backend.rbc.output;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class CurrencyResponseBean {
    @JsonProperty("date")
    private String date;

    @JsonProperty("eur")
    private CurrencyDetailsBean currency;

//    @JsonProperty("usd")
//    private CurrencyDetailsBean currencyUsd;
//
//    @JsonProperty("rsd")
//    private CurrencyDetailsBean currencyRsd;
//
//    @JsonProperty("aed")
//    private CurrencyDetailsBean currencyAed;

}
