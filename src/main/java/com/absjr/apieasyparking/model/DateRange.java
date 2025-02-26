package com.absjr.apieasyparking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DateRange {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
