package com.absjr.apieasyparking.exeption;

public
class LicensePlateNotFoundException extends RuntimeException {
    public
    LicensePlateNotFoundException(String message) {
        super("License plate not found: " + message);
    }
}
