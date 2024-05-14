package com.piisw.cinema.controller;

import com.piisw.cinema.model.DTO.CheckTicketDTO;
import com.piisw.cinema.model.DTO.PurchaseTicketResponseDTO;
import com.piisw.cinema.model.entity.Seat;
import com.piisw.cinema.model.entity.Ticket;
import com.piisw.cinema.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseTicketResponseDTO> purchaseTicket(@RequestParam UUID screeningId, @RequestBody List<Seat> seats, @RequestParam UUID userId) {
        System.out.println("Seats: " + seats);
        PurchaseTicketResponseDTO ticket = ticketService.purchaseTicket(screeningId, seats, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PatchMapping("/check")
    public ResponseEntity<CheckTicketDTO> purchaseTicket(@RequestParam UUID ticketId, @RequestParam UUID userId) {

        CheckTicketDTO checkedTicket = ticketService.checkTicket(ticketId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkedTicket);
    }
}
