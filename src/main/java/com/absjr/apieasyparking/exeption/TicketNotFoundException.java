package com.absjr.apieasyparking.exeption;

public
class TicketNotFoundException extends RuntimeException {
    public
    TicketNotFoundException(String message) {
        super("Ticket not found: " + message);
    }
}
