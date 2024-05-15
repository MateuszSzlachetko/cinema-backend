package com.piisw.cinema.service;

import com.piisw.cinema.model.DTO.MovieResponseDTO;
import com.piisw.cinema.model.DTO.ScreeningResponseDTO;
import com.piisw.cinema.model.DTO.ScreeningsAndMoviesDTO;
import com.piisw.cinema.model.entity.Movie;
import com.piisw.cinema.model.entity.MoviePoster;
import com.piisw.cinema.model.entity.Screening;
import com.piisw.cinema.model.entity.ScreeningRoom;
import com.piisw.cinema.repository.ScreeningRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    private LocalDate date;
    private Screening screening;
    private Movie movie;
    private UUID screeningId;

    @BeforeEach
    public void setUp() {
        date = LocalDate.now();
        screeningId = UUID.randomUUID();

        movie = new Movie();
        movie.setId(UUID.randomUUID());
        movie.setTitle("Test Movie");
        movie.setMoviePoster(new MoviePoster());

        screening = new Screening();
        screening.setId(screeningId);
        screening.setScreeningRoom(new ScreeningRoom(1,"main hall",null,null));
        screening.setStartDate(date.atStartOfDay());
        screening.setMovie(movie);
    }

    @Test
    public void testGetScreeningsByDate() {
        List<Screening> screenings = List.of(screening);

        when(screeningRepository.findScreeningsByStartDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(screenings);

        ScreeningsAndMoviesDTO result = screeningService.getScreeningsByDate(date);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getScreeningsResponseDTO().size());
        Assertions.assertEquals(1, result.getMoviesResponseDTO().size());

        ScreeningResponseDTO screeningDTO = result.getScreeningsResponseDTO().get(0);
        Assertions.assertEquals(screening.getId(), screeningDTO.getId());

        MovieResponseDTO movieDTO = result.getMoviesResponseDTO().iterator().next();
        Assertions.assertEquals(movie.getId(), movieDTO.getId());
    }

    @Test
    public void testGetScreeningById_Success() {
        when(screeningRepository.findById(screeningId)).thenReturn(Optional.of(screening));

        Optional<Screening> result = screeningService.getScreeningById(screeningId);

        assertTrue(result.isPresent());
        Assertions.assertEquals(screening.getId(), result.get().getId());
    }

    @Test
    public void testGetScreeningById_NotFound() {
        when(screeningRepository.findById(screeningId)).thenReturn(Optional.empty());

        Optional<Screening> result = screeningService.getScreeningById(screeningId);

        assertFalse(result.isPresent());
    }
}