package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.service.CarFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/car-fare")
public
class CarFareController {

    @Autowired
    private
    CarFareService carFareService;

    @PostMapping
    public
    ResponseEntity<CarFareDTO> createCarFare(@RequestBody CarFareDTO carFareDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carFareService.createCarFare(carFareDTO));

    }
}
