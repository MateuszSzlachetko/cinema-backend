package com.piisw.cinema.service;

import com.piisw.cinema.model.entity.Ticket;
import com.piisw.cinema.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByScreeningId(UUID screeningId){
        return this.ticketRepository.findTicketsByScreeningId(screeningId);
    }
}
