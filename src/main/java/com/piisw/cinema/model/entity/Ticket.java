package com.piisw.cinema.model.entity;

import com.piisw.cinema.model.enums.TicketState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private LocalDateTime purchaseDate;

    @NotNull
    private UUID reservationCode;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TicketState state;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    private User customer;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    @NotNull
    private Screening screening;

    @OneToMany(mappedBy = "ticket")
    @NotNull
    private Set<SeatReservation> reservatedSeats;
}