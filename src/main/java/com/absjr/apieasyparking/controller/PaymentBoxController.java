package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.PaymentBoxDTO;
import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.service.PaymentBoxService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/parking")
public class PaymentBoxController {

    @Autowired
    private PaymentBoxService paymentBoxService;

    @Operation(summary = "Create Payment Box")
    @PostMapping()
    public ResponseEntity<PaymentBoxDTO> createPaymentBox(String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentBoxService.createPaymentBox(name));
    }

    @Operation(summary = "Make the payment by entering the vehicle license plate")
    @PostMapping("/payment/{plate}")
    public ResponseEntity<TicketDTO> payment(@PathVariable String plate) {
        TicketDTO paidTicket = paymentBoxService.payment(plate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paidTicket);
    }

    @PostMapping("/permanence/{plate}")
    public ResponseEntity<String> timePermanence(@PathVariable String plate) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentBoxService.timePermanence(plate));
    }

}
