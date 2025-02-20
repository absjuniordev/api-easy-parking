package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.repository.CarFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarFareService {

    @Autowired
    private CarFareRepository carFareRepository;


}
