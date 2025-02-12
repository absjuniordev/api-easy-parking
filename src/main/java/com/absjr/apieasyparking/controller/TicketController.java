package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.AcessDTO;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.service.PaymentBoxService;
import com.absjr.apieasyparking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/tickets")
public
class TicketController {

   @Autowired
   private TicketService ticketService;

   @PostMapping
   public  ResponseEntity<Ticket> createTicket(@RequestBody AcessDTO createTKT){
       Ticket ticket = ticketService.createTicket(createTKT.getPlate(), createTKT.getVehicleType(), createTKT.getOperatorName());
       return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
   }


}
