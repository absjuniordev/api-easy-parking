package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.LicensePlate;

import java.util.List;
import java.util.stream.Collectors;

public class LicensePlateDTO {

    private String plate;
    private String vehicleType;
    private List<TicketDTO> tickets;

    public LicensePlateDTO() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public LicensePlateDTO(LicensePlate licensePlate) {
        this.plate = licensePlate.getPlate();
        this.vehicleType = licensePlate.getVehicleType().name();
        this.tickets = licensePlate.getTickets().stream()
                .map(TicketDTO::new)
                .collect(Collectors.toList());
    }
}
