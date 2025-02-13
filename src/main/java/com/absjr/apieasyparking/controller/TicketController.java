package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.AcessDTO;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.service.PaymentBoxService;
import com.absjr.apieasyparking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

   @GetMapping("{ticket}")
    public ResponseEntity<Ticket> findByTicket(@PathVariable String ticket){
       Ticket tkt =   ticketService.findByTicket(ticket);
     return  ResponseEntity.status(HttpStatus.OK).body(tkt);
   }

    @GetMapping
    public ResponseEntity<List<Ticket>> gelAllTicket(){
        List<Ticket> ticket = ticketService.getAllTicket();
        return  ResponseEntity.status(HttpStatus.OK).body(ticket);
    }


}
