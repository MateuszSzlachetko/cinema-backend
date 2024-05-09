package com.piisw.cinema.controller;

import com.piisw.cinema.model.DTO.ScreeningRoomDTO;
import com.piisw.cinema.service.ScreeningRoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/screening-rooms")
@AllArgsConstructor
public class ScreeningRoomController {
    private final ScreeningRoomService screeningRoomService;

    @GetMapping("/screenings/{screeningId}")
   public ScreeningRoomDTO getScreeningRoomByScreeningId(@PathVariable UUID screeningId){
        ScreeningRoomDTO screeningRoomDTO = this.screeningRoomService.
    }
}
