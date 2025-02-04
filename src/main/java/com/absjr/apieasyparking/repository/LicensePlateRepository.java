package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate, String> {
    LicensePlate findByPlate(String plate);
}
