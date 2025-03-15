package com.absjr.apieasyparking.controller;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.model.DateRange;
import com.absjr.apieasyparking.model.PlateUpdate;
import com.absjr.apieasyparking.service.LicensePlateService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Find a vehicle by license plate")
    @GetMapping("/{plate}")
    public
    ResponseEntity<LicensePlateDTO> findByPlate(@PathVariable String plate) {
        LicensePlateDTO licensePlateDTO = licensePlateService.findByPlate(plate);
        return ResponseEntity.ok().body(licensePlateDTO);
    }

    @Operation(summary = "Delete a license plate by inserting a license plate")
    @DeleteMapping("/{plate}")
    public
    ResponseEntity<String> deletePlate(@PathVariable String plate) {
        licensePlateService.delete(plate);
        return ResponseEntity.ok("Delete successful");
    }

    @Operation(summary = "Find all vehicle plates of the day")
    @GetMapping
    ResponseEntity<List<String>> findLicensePlateForToday(){
        return  ResponseEntity.ok().body(licensePlateService.findLicensePlateForToday());
    }

    @Operation(summary = "Filter board searches by day")
    @GetMapping("/searchByDate")
    ResponseEntity<List<String>> findLicensePlateByEntryTimeBetween(@RequestBody DateRange dateRange){
        return ResponseEntity.ok().body(licensePlateService.findLicensePlateByEntryTimeBetween(dateRange.getStartDate(), dateRange.getEndDate()));
    }

    @Operation(summary = "Change the vehicle's license plate ")
    @PutMapping("/update")
    ResponseEntity<LicensePlateDTO> updateLicensePlate(@RequestBody PlateUpdate plateUpdate){
        return ResponseEntity.ok().body(licensePlateService.updateLicensePlate(plateUpdate.getActualPlate(), plateUpdate.getNewPlate()));
    }

}
