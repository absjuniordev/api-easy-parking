package com.absjr.apieasyparking.entity;

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
@Table(name = "tb_payment_box")
public class PaymentBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operatorName;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentBox", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();;

    public PaymentBox() {
    }

    public PaymentBox(String operatorName, List<Ticket> tickets) {
        this.operatorName = operatorName;
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentBox that = (PaymentBox) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
