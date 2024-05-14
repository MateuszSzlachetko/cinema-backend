package com.piisw.cinema.model.DTO;

import com.piisw.cinema.model.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PurchaseTicketRequestDTO {

    private UUID screeningId;

    private List<Seat> seats;
}
