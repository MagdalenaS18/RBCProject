package com.backend.rbc.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CurrencyDetailsBean {
    @JsonProperty("aed")
    private String aedCurrency;
    @JsonProperty("bam")
    private String bamCurrency;
    @JsonProperty("chf")
    private String chfCurrency;
    @JsonProperty("rsd")
    private String rsdCurrency;
    @JsonProperty("rub")
    private String rubCurrency;
    @JsonProperty("cad")
    private String cadCurrency;
    @JsonProperty("usd")
    private String usdCurrency;
    @JsonProperty("eur")
    private String eurCurrency;
}
