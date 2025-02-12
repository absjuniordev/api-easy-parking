package com.absjr.apieasyparking.controller;
import com.absjr.apieasyparking.entity.DTO.AcessDTO;
import com.absjr.apieasyparking.entity.Ticket;
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


}
