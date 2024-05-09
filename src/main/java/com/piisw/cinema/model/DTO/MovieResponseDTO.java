package com.piisw.cinema.model.DTO;

import com.piisw.cinema.model.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MovieResponseDTO {

    private UUID id;

    private String title;

    private String description;

    private Integer duration;

    private String moviePosterAccessURL;

    public static MovieResponseDTO map(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .moviePosterAccessURL(movie.getMoviePoster().getAccessUrl())
                .build();
    }
}
