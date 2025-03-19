package com.absjr.apieasyparking.exeption;

public
class FareNotFoundException extends RuntimeException {
    public
    FareNotFoundException(String message) {
        super("Fare not found: " + message);
    }
}
