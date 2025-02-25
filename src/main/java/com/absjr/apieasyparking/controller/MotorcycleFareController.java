package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.BikeFareDTO;
import com.absjr.apieasyparking.service.BikeFareService;
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
    BikeFareService bikeFareService;

    @PostMapping
    public
    ResponseEntity<BikeFareDTO> createCarFare(@RequestBody BikeFareDTO bikeFareDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bikeFareService.createMotorcycleFare(bikeFareDTO));

    }
}
