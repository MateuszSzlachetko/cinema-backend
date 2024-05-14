package com.piisw.cinema.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.piisw.cinema.model.enums.Column;
import com.piisw.cinema.model.enums.Row;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "seat")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Row row;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Column column;

    @OneToMany(mappedBy = "seat")
    private Set<SeatReservation> seatReservations;

    @ManyToOne
    @JoinColumn(name = "screening_room_id")
    private ScreeningRoom screeningRoom;

}
