package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.model.Access;
import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.model.DateRange;
import com.absjr.apieasyparking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public
class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public
    ResponseEntity<TicketDTO> createTicket(@RequestBody Access createTKT) {
        Ticket ticket = ticketService.createTicket(createTKT.getPlate(), createTKT.getVehicleType(), createTKT.getOperatorName());
        return ResponseEntity.status(HttpStatus.CREATED).body(new TicketDTO(ticket));
    }

    @GetMapping("/{ticket}")
    public
    ResponseEntity<TicketDTO> findByTicket(@PathVariable String ticket) {
        TicketDTO findTicket = ticketService.findByTicket(ticket);
        return ResponseEntity.ok().body(findTicket);
    }

    @GetMapping
    public
    ResponseEntity<List<String>> findTicketsForToday() {
        return ResponseEntity.ok().body(ticketService.findTicketsForToday());
    }

    @GetMapping("/paid/{paid}")
    public ResponseEntity<List<String>> findTicketsByPaidStatusAndDate(@PathVariable Boolean paid) {
        return ResponseEntity.ok().body(ticketService.findTicketsByPaidStatusAndDate(paid));
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<String>> findTicketsByEntryTimeBetween(@RequestBody DateRange dateRange ){
        return ResponseEntity.ok().body(ticketService.findTicketsByEntryTimeBetween(dateRange.getStartDate(), dateRange.getEndDate()));
    }

}
