package com.absjr.apieasyparking.exeption;

public
class FareNotFoundException extends RuntimeException {
    public
    FareNotFoundException(String msg) {
        super("Fare not found: " + msg);
    }
}
