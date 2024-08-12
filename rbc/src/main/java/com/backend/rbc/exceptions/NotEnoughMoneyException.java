package com.backend.rbc.exceptions;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException() {
        super(ErrorMessages.NOT_ENOUGH_MONEY.getMessage());
    }
}
