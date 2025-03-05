package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public
class LicensePlateService {

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @Transactional(readOnly = true)
    public
    LicensePlate getOrCreateLicensePlate(String plate, String vehicleType) {
        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);
        if (existingPlate == null) {
            VehicleType newVehicle = VehicleType.valueOf(vehicleType.toUpperCase());
            existingPlate = new LicensePlate(plate, newVehicle);
            return licensePlateRepository.save(existingPlate);
        }
        return existingPlate;
    }

    @Transactional(readOnly = true)
    public
    LicensePlateDTO findByPlate(String plate) {
        LicensePlate licensePlate = licensePlateRepository.findByPlate(plate);
        if (licensePlate == null) {
            throw new LicensePlateNotFoundException("The Plate " + plate + " is null");
        }
        return new LicensePlateDTO(licensePlate);
    }

    @Transactional
    public
    void delete(String plate) {
        if (!licensePlateRepository.existsById(plate)) {
            throw new LicensePlateNotFoundException("This Plate [" + plate + "] does not exist");
        }
        licensePlateRepository.deleteById(plate);
    }

    @Transactional
    public void deleteAll(){
        licensePlateRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<String> findLicensePlateByEntryTimeBetween( LocalDate startDate, LocalDate endDate){
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        List<String> resultSearch = licensePlateRepository.findLicensePlateByEntryTimeBetween(startDateTime, endDateTime)
                .stream()
                .map(LicensePlate::getPlate)
                .collect(Collectors.toList());

        if (resultSearch.isEmpty()) throw new LicensePlateNotFoundException("The result is empty");

        return resultSearch;
    }

    @Transactional(readOnly = true)
    public
    List<String> findLicensePlateForToday() {
        List<String> resultSearch = licensePlateRepository.findLicensePlateForToday()
                .stream()
                .map(LicensePlate::getPlate)
                .collect(Collectors.toList());
        if (resultSearch.isEmpty()) throw new LicensePlateNotFoundException("The list is empty");
        return resultSearch;
    }

    @Transactional
    public LicensePlateDTO updateLicensePlate(String plate, String newPlate){
        LicensePlate licensePlate = licensePlateRepository.findByPlate(plate);
        if (licensePlate == null) {
            throw new LicensePlateNotFoundException("The Plate " + plate + " is null");
        }
        licensePlate.setPlate(newPlate);
       return new LicensePlateDTO( licensePlateRepository.save(licensePlate));
    }


}

