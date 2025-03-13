package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.LicensePlate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class LicensePlateDTO {

    private String plate;
    private String vehicleType;
    private List<TicketDTO> tickets;

    public LicensePlateDTO(LicensePlate licensePlate) {
        this.plate = licensePlate.getPlate();
        this.vehicleType = licensePlate.getVehicleType().name();
        this.tickets = licensePlate.getTickets().stream()
                .map(TicketDTO::new)
                .collect(Collectors.toList());
    }


}
