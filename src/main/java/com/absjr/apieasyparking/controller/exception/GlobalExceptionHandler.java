package com.absjr.apieasyparking.controller.exception;

import com.absjr.apieasyparking.exeption.FareNotFoundException;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.exeption.PaymentBoxException;
import com.absjr.apieasyparking.exeption.TicketNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public
class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LicensePlateNotFoundException.class)
    public ResponseEntity<String> handleLicensePlateNotFoundException(LicensePlateNotFoundException exception) {
        String message = exception.getMessage();
        logger.error("Error: ", exception);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException exception) {
        String message = exception.getMessage();
        logger.error("Error: ", exception);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentBoxException.class)
    public ResponseEntity<String> handlePaymentBoxException(PaymentBoxException exception) {
        String message = exception.getMessage();
        logger.error("Error: ", exception);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FareNotFoundException.class)
    public ResponseEntity<String> handleFareException(FareNotFoundException exception){
        String message = exception.getMessage();
        logger.error("Error: " , exception);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
