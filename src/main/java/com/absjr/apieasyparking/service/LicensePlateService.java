package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public
    void delete(String plate) {
        if (!licensePlateRepository.existsById(plate)) {
            throw new LicensePlateNotFoundException("This Plate [" + plate + "] does not exist");
        }
        licensePlateRepository.deleteById(plate);
    }

    @Transactional(readOnly = true)
    public
    List<LicensePlate> getAllPlate() {
        List<LicensePlate> resultSearch = licensePlateRepository.findAll();
        if (resultSearch.isEmpty()) throw new LicensePlateNotFoundException("The list is empty");
        return resultSearch;
    }
}

