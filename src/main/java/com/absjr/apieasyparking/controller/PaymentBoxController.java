package com.absjr.apieasyparking.controller;
import com.absjr.apieasyparking.service.PaymentBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class PaymentBoxController {

    @Autowired
    private PaymentBoxService paymentBoxService;

    @GetMapping("/{plate}")
    public ResponseEntity<String> payment(@PathVariable String plate) {
        paymentBoxService.payment(plate);
        return ResponseEntity.ok("Payment Sucefull");
    }


}
