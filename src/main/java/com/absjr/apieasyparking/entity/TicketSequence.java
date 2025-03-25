package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
@Entity
@Table(name = "tb_ticket_sequence ")
public class TicketSequence {
    @Id
    private Long id;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Long sequencerNumber;

    @Override
    public
    boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketSequence that = (TicketSequence) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public
    int hashCode() {
        return Objects.hashCode(id);
    }
}
