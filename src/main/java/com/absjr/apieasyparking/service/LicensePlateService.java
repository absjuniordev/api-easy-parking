package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.repository.LicensePlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicensePlateService {

    @Autowired
    private LicensePlateRepository licensePlateRepository;


}
