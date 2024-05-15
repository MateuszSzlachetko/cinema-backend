package com.piisw.cinema.repository;

import com.piisw.cinema.model.entity.Ticket;
import com.piisw.cinema.model.enums.TicketState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findTicketsByScreeningId(UUID screeningId);

    List<Ticket> findByStateOrState(TicketState stateOne, TicketState stateTwo);
}
