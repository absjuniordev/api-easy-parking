package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.AcessDTO;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.service.PaymentBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public
class TicketController {

    @Autowired
    private
    PaymentBoxService paymentBoxService;

    @PostMapping
    public
    ResponseEntity<Ticket> createTick(@RequestBody AcessDTO newAccess){
        Ticket ticket = paymentBoxService.createAccess(newAccess.getPlate(), newAccess.getVehicleType(), newAccess.getOperatorName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }
}
