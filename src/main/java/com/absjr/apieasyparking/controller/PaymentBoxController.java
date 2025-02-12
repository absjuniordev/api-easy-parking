package com.absjr.apieasyparking.controller;
import com.absjr.apieasyparking.service.PaymentBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class PaymentBoxController {

    @Autowired
    private PaymentBoxService paymentBoxService;







}
