package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.service.LicensePlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plate")
public
class LicensePlateController {

    @Autowired
    private
    LicensePlateService licensePlateService;

    //create palet

    @GetMapping("/{plate}")
    public
    ResponseEntity<LicensePlateDTO> getPlate(@PathVariable String plate) {
        LicensePlateDTO licensePlateDTO = licensePlateService.getPlate(plate);
        return ResponseEntity.ok().body(licensePlateDTO);
    }

    @DeleteMapping("{plate}")
    public
    ResponseEntity<String> deletePlate(@PathVariable String plate) {
        licensePlateService.delete(plate);
        return ResponseEntity.ok("Delete successful");
    }
}
