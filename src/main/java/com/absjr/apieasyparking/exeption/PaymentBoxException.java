package com.absjr.apieasyparking.exeption;

public
class PaymentBoxException extends RuntimeException{
    public
    PaymentBoxException(String msg) {
        super("Error: "+ msg);
    }
}
