package com.absjr.apieasyparking.exeption;

public
class LicensePlateNotFoundException extends  RuntimeException{
    public LicensePlateNotFoundException(String msg){
        super("License plate not found: " + msg);
    }
}
