package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.AcessDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import com.absjr.apieasyparking.service.PaymentBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class PaymentBoxController {
    @Autowired
    private PaymentBoxService paymentBoxService;

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @PostMapping
    public ResponseEntity<Ticket> creaTeTick(@RequestBody AcessDTO newAcess){
         Ticket ticket = paymentBoxService.creteAcess(newAcess.getPlate(), newAcess.getVehicleType(), newAcess.getOperatorName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping(value = "/{plate}")
    public ResponseEntity<LicensePlate> getPlate(@PathVariable String plate){
      LicensePlate licensePlate=  licensePlateRepository.findByPlate(plate);
        return  ResponseEntity.ok().body(licensePlate);
    }
}
