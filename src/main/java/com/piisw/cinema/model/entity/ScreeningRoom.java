package com.piisw.cinema.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "screening_room")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "screeningRoom")
    private Set<Seat> seats;

    @OneToMany(mappedBy = "screeningRoom")
    private Set<Screening> screenings;
}
