package com.absjr.apieasyparking.controller;
import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.service.PaymentBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class PaymentBoxController {

    @Autowired
    private PaymentBoxService paymentBoxService;

    @PostMapping("/{plate}")
    public ResponseEntity<TicketDTO> payment(@PathVariable String plate) {
      TicketDTO paidTicket = paymentBoxService.payment(plate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paidTicket);
    }


}
