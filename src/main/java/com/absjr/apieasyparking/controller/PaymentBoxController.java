package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.AcessDTO;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.entity.Ticket;
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


    @PostMapping
    public ResponseEntity<Ticket> createTick(@RequestBody AcessDTO newAcess){
         Ticket ticket = paymentBoxService.createAccess(newAcess.getPlate(), newAcess.getVehicleType(), newAcess.getOperatorName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping(value = "/{plate}")
    public ResponseEntity<LicensePlateDTO> getPlate(@PathVariable String plate) {
        LicensePlateDTO licensePlateDTO = paymentBoxService.getPlate(plate);
        return ResponseEntity.ok().body(licensePlateDTO);
    }

    @DeleteMapping(value = "/{plate}")
    public
    ResponseEntity<String> delete(@PathVariable String plate){
        paymentBoxService.delete(plate);
        return ResponseEntity.ok("Delete successful");
    }

}
