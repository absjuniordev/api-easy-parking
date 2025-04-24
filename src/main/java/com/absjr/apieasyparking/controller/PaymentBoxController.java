package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.CreatePaymentBoxDto;
import com.absjr.apieasyparking.entity.DTO.PaymentBoxDTO;
import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.enums.PaymentMethods;
import com.absjr.apieasyparking.service.PaymentBoxService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class PaymentBoxController {

    @Autowired
    private PaymentBoxService paymentBoxService;

    @Operation(summary = "Create Payment Box")
    @PostMapping
    public ResponseEntity<PaymentBoxDTO> createPaymentBox(@RequestBody CreatePaymentBoxDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentBoxService.createPaymentBox(request.getName()));
    }

    @Operation(summary = "Make the payment by entering the vehicle license plate")
    @PostMapping("/payment/{plate}/{paymentMethod}")
    public ResponseEntity<TicketDTO> payment(@PathVariable String plate, @PathVariable PaymentMethods paymentMethod) {
        TicketDTO paidTicket = paymentBoxService.payment(plate, paymentMethod);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paidTicket);
    }

    @PostMapping("/permanence/{plate}")
    public ResponseEntity<String> timePermanence(@PathVariable String plate) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentBoxService.timePermanence(plate));
    }

}
