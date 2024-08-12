package com.backend.rbc.exceptions;

public class AmountCanNotBeNegativeException extends RuntimeException{
    public AmountCanNotBeNegativeException() {
        super(ErrorMessages.AMOUNT_CANNOT_BE_NEGATIVE.getMessage());
    }
}
