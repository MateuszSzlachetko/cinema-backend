package com.piisw.cinema.model.DTO;

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
public class PurchaseTicketResponseDTO {

    private LocalDateTime purchaseDate;

    private UUID reservationCode;

    private UUID screeningId;

    private String movieTitle;
}
