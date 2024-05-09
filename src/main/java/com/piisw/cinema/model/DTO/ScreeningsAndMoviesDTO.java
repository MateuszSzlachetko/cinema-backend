package com.piisw.cinema.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ScreeningsAndMoviesDTO {

    @JsonProperty("screenings")
    private List<ScreeningResponseDTO> screeningsResponseDTO;

    @JsonProperty("movies")
    private Set<MovieResponseDTO> moviesResponseDTO;
}
