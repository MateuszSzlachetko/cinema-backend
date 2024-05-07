package com.piisw.cinema.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "movie")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Integer duration;

    @OneToMany(mappedBy = "movie")
    private Set<Screening> screenings;

    @OneToOne
    @JoinColumn(name = "movie_poster_id")
    private MoviePoster moviePoster;
}
