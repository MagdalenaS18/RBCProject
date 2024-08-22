package com.backend.rbc.exceptions;

public class SettingsNotFoundException extends RuntimeException {
    public SettingsNotFoundException(){
        super(ErrorMessages.SETTINGS_NOT_FOUND.getMessage());
    }
}
