package com.piisw.cinema.model.DTO;

import com.piisw.cinema.model.enums.TicketState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CheckTicketDTO {
    private LocalDateTime purchaseDate;

    private TicketState ticketState;

    private UUID customerId;

    private UUID reservationCode;

    private UUID screeningId;

    private String movieTitle;

    private String checkMessage;
}
