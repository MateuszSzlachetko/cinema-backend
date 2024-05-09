package com.piisw.cinema.controller;

import com.piisw.cinema.model.DTO.ScreeningsAndMoviesDTO;
import com.piisw.cinema.service.ScreeningService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/screenings")
@AllArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;


    @GetMapping
    private ResponseEntity<ScreeningsAndMoviesDTO> getScreeningsByDate(@RequestParam LocalDate date) {
        ScreeningsAndMoviesDTO result = this.screeningService.getScreeningsByDate(date);
        return ResponseEntity.ok(result);
    }
}
