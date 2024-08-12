package com.backend.rbc.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super(ErrorMessages.INVALID_CREDENTIALS.getMessage());
    }
}
