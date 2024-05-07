package com.piisw.cinema.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "movie_poster")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePoster {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String accessUrl;

    @NotNull
    private String fileName;

    @NotNull
    private String filePath;

    @OneToOne(mappedBy = "moviePoster")
    private Movie movie;
}
