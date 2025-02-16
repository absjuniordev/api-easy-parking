package com.absjr.apieasyparking.exeption;

public
class TicketNotFoundException extends RuntimeException {
    public
    TicketNotFoundException(String msg) {
        super("Ticket not found: " + msg);
    }
}
