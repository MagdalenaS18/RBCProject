package com.backend.rbc.exceptions;

public class UnableToFetchCurrenciesException extends RuntimeException{
    public UnableToFetchCurrenciesException() {
        super(ErrorMessages.UNABLE_TO_FETCH_CURRENCIES.getMessage());
    }
}
