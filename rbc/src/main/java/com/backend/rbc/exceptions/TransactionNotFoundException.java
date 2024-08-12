package com.backend.rbc.exceptions;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException() {
        super(ErrorMessages.TRANSACTION_NOT_FOUND.getMessage());
    }
}
