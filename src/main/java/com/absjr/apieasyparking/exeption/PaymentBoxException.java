package com.absjr.apieasyparking.exeption;

public
class PaymentBoxException extends RuntimeException{
    public
    PaymentBoxException(String message) {
        super("Error: "+ message);
    }
}
