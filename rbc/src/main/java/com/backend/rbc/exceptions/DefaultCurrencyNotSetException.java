package com.backend.rbc.exceptions;

public class DefaultCurrencyNotSetException extends RuntimeException {
    public DefaultCurrencyNotSetException() {
        super(ErrorMessages.DEFAULT_CURRENCY_NOT_SET.getMessage());
    }
}
