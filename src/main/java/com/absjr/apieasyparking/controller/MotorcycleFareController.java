package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.entity.DTO.MotorcycleFareDTO;
import com.absjr.apieasyparking.service.CarFareService;
import com.absjr.apieasyparking.service.MotorcycleFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/motorcycle-fare")
public
class MotorcycleFareController {

    @Autowired
    private
    MotorcycleFareService  motorcycleFareService;

    @PostMapping
    public
    ResponseEntity<MotorcycleFareDTO> createCarFare(@RequestBody MotorcycleFareDTO  motorcycleFareDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motorcycleFareService.createCarFare(motorcycleFareDTO));

    }
}
