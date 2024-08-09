package com.backend.rbc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    EXPENSE("EXPENSE"),
    PROFIT("PROFIT");

    private final String typeValue;
}
