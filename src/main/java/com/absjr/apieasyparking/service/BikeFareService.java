package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.BikeFare;
import com.absjr.apieasyparking.entity.DTO.BikeFareDTO;
import com.absjr.apieasyparking.repository.BikeFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class BikeFareService extends BaseFareService<BikeFare, BikeFareDTO> {

    @Autowired
    private BikeFareRepository bikeFareRepository;

    @Override
    protected BikeFare findFare() {
        return bikeFareRepository.findFirstBikeFare().orElse(null);
    }

    @Override
    protected BikeFareDTO createFareDTO(BikeFare fare) {
        return new BikeFareDTO(fare);
    }

    public BikeFareDTO createBikeFare(BikeFareDTO bikeFareDTO) {
        BikeFare bikeFare = new BikeFare(
                bikeFareDTO.getValueFare(),
                bikeFareDTO.getAdditionalValue(),
                bikeFareDTO.getOvernight(),
                bikeFareDTO.getMinimumStay(),
                bikeFareDTO.getAdditionalStay(),
                bikeFareDTO.getStartOvernight(),
                bikeFareDTO.getEndOvernight()
        );

        bikeFareRepository.save(bikeFare);
        return new BikeFareDTO(bikeFare);
    }

    public
    BigDecimal calculateBikeFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime) {
        return calculateFare(duration, entryTime, departureTime);
    }
}
