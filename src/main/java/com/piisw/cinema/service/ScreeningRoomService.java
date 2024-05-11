package com.piisw.cinema.service;

import com.piisw.cinema.controller.ScreeningRoomController;
import com.piisw.cinema.model.DTO.ScreeningRoomDTO;
import com.piisw.cinema.model.DTO.SeatDTO;
import com.piisw.cinema.model.entity.*;
import com.piisw.cinema.repository.ScreeningRoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ScreeningRoomService {
    private final ScreeningRoomRepository screeningRoomRepository;
    private final ScreeningService screeningService;
    private final TicketService ticketService;

    public ScreeningRoomDTO getScreeningRoomByScreeningId(UUID id) {
        Optional<Screening> screening = this.screeningService.getScreeningById(id);
        if (screening.isEmpty()) {
            throw new NoSuchElementException("Nie znaleziono seansu z takim id");
        }

        ScreeningRoom screeningRoom = screening.get().getScreeningRoom();

        Set<Ticket> tickets = new HashSet<>(ticketService.getTicketsByScreeningId(id));

        Set<SeatReservation> seatReservations = tickets.stream()
                .map(Ticket::getReservatedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Set<SeatDTO> seatDTOS = new HashSet<>();
        for (Seat seat : screeningRoom.getSeats()) {
            Boolean isFree = seatReservations.stream()
                    .noneMatch(reservation -> reservation.getSeat().getId().equals(seat.getId()));
            seatDTOS.add(new SeatDTO(seat.getId(), seat.getRow(), seat.getColumn(), isFree));
        }

        return new ScreeningRoomDTO(screeningRoom.getId(), screeningRoom.getName(), seatDTOS);
    }

}