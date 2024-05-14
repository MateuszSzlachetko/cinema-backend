package com.piisw.cinema.controller;

import com.piisw.cinema.model.DTO.CheckTicketDTO;
import com.piisw.cinema.model.DTO.PurchaseTicketRequestDTO;
import com.piisw.cinema.model.DTO.PurchaseTicketResponseDTO;
import com.piisw.cinema.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseTicketResponseDTO> purchaseTicket(@RequestBody PurchaseTicketRequestDTO purchaseTicketRequestDTO) {
        System.out.println("Seats: " + purchaseTicketRequestDTO.getSeats());
        PurchaseTicketResponseDTO ticket = ticketService.purchaseTicket(purchaseTicketRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PatchMapping("/check")
    public ResponseEntity<CheckTicketDTO> checkTicket(@RequestParam UUID ticketId) {

        CheckTicketDTO checkedTicket = ticketService.checkTicket(ticketId);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkedTicket);
    }
}
