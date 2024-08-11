package com.backend.rbc.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CurrencyResponseBean {
    @JsonProperty("date")
    private String date;

    @JsonProperty("eur")
    private CurrencyDetailsBean currency;
}
