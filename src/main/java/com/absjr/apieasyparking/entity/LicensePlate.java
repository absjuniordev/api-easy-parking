package com.absjr.apieasyparking.entity;

import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_license_table")
public class LicensePlate {

    @Id
    private String plate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


    @OneToMany(mappedBy = "licensePlate", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();


    public LicensePlate() {
    }

    public LicensePlate(String plate, VehicleType vehicleType) {
        this.plate = plate;
        this.vehicleType = vehicleType;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(plate, that.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plate);
    }
}
