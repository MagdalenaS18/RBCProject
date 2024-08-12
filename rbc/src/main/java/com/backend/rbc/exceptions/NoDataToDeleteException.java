package com.backend.rbc.exceptions;

public class NoDataToDeleteException extends RuntimeException{
    public NoDataToDeleteException() {
        super(ErrorMessages.NO_DATA_TO_DELETE.getMessage());
    }
}
