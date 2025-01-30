package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class PaymentBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operatorName;

    @OneToMany(mappedBy = "payment")
    private List<Ticket> tickets;

    public PaymentBox() {
    }

    public PaymentBox(String operatorName, List<Ticket> tickets) {
        this.operatorName = operatorName;
        this.tickets = tickets;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
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
