package com.backend.rbc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleAccountNotFoundException(AccountNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleTransactionNotFoundException(TransactionNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotEnoughMoneyException(NotEnoughMoneyException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(AmountCanNotBeNegativeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAmountCanNotBeNegative(AmountCanNotBeNegativeException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(NoDataToDeleteException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErrorResponse handleNoDataToDeleteException(NoDataToDeleteException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(UnableToFetchCurrenciesException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUnableToFetchCurrenciesException(UnableToFetchCurrenciesException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(DefaultCurrencyNotSetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleDefaultCurrencyNotSetException(DefaultCurrencyNotSetException exception){
        return new ErrorResponse(exception.getMessage());
    }

}
