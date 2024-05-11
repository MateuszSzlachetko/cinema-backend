package com.piisw.cinema.service;

import com.piisw.cinema.model.DTO.MovieResponseDTO;
import com.piisw.cinema.model.DTO.ScreeningResponseDTO;
import com.piisw.cinema.model.DTO.ScreeningsAndMoviesDTO;
import com.piisw.cinema.model.entity.Screening;
import com.piisw.cinema.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningsAndMoviesDTO getScreeningsByDate(LocalDate date) {
        List<Screening> screenings = this.screeningRepository
                .findScreeningsByStartDateBetween(
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay());

        List<ScreeningResponseDTO> screeningsDTO = screenings.stream()
                .map(ScreeningResponseDTO::map)
                .toList();

        Set<MovieResponseDTO> moviesOfScreeningsDTO = screenings.stream()
                .map(Screening::getMovie)
                .map(MovieResponseDTO::map)
                .collect(Collectors.toSet());

        return new ScreeningsAndMoviesDTO(screeningsDTO, moviesOfScreeningsDTO);
    }

    public Optional<Screening> getScreeningById(UUID id){
        return this.screeningRepository.findById(id);
    }
}
