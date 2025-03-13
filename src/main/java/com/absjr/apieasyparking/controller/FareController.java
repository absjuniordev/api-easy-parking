package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.FareDTO;
import com.absjr.apieasyparking.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/fare")
public
class FareController {

    @Autowired
    private
    FareService fareService;

    @PostMapping
    public
    ResponseEntity<FareDTO> createFare(@RequestBody FareDTO fareDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fareService.createFare(fareDTO));
    }
}
