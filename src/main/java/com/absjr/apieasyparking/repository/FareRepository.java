package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare,Long > {
}
