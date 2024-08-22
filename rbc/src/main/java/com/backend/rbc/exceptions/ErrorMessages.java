package com.backend.rbc.exceptions;

public enum ErrorMessages {
    ACCOUNT_NOT_FOUND("Account Not Found."),
    TRANSACTION_NOT_FOUND("Transaction Not Found."),
    NOT_ENOUGH_MONEY("You don't have enough money to complete this transaction!"),
    AMOUNT_CANNOT_BE_NEGATIVE("The Amount must be Positive Number!"),
    NO_DATA_TO_DELETE("There's no Data left to Delete."),
    INVALID_CREDENTIALS("Invalid Credentials! Please, check Your Inputs."),
    UNABLE_TO_FETCH_CURRENCIES("Unable to fetch conversion rate!"),
    DEFAULT_CURRENCY_NOT_SET("Default currency not set in settings"),
    SETTINGS_NOT_FOUND("Settings Not Found.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
