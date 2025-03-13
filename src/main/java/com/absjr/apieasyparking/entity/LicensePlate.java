package com.absjr.apieasyparking.entity;

import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "tb_license_plate")
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @JsonIgnore
    @OneToMany(mappedBy = "licensePlate", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();


    public LicensePlate() {
    }

    public LicensePlate(String plate, VehicleType vehicleType) {
        this.plate = plate;
        this.vehicleType = vehicleType;
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
