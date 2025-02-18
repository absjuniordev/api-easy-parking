package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.service.LicensePlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plate")
public
class LicensePlateController {

    @Autowired
    private
    LicensePlateService licensePlateService;

    @GetMapping("/{plate}")
    public
    ResponseEntity<LicensePlateDTO> findByPlate(@PathVariable String plate) {
        LicensePlateDTO licensePlateDTO = licensePlateService.findByPlate(plate);
        return ResponseEntity.ok().body(licensePlateDTO);
    }

    @DeleteMapping("/{plate}")
    public
    ResponseEntity<String> deletePlate(@PathVariable String plate) {
        licensePlateService.delete(plate);
        return ResponseEntity.ok("Delete successful");
    }

    @GetMapping
    ResponseEntity<List<LicensePlate>> getAllPlate(){
        List<LicensePlate> plate =  licensePlateService.getAllPlate();
        return  ResponseEntity.ok().body(plate);
    }
}
