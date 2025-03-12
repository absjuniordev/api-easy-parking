package com.absjr.apieasyparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bike-fare")
public
class BikeFareController {

    @Autowired
    private
    BikeFareService bikeFareService;

    @PostMapping
    public
    ResponseEntity<BikeFareDTO> createBikeFare(@RequestBody BikeFareDTO bikeFareDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bikeFareService.createBikeFare(bikeFareDTO));

    }
}
