package com.piisw.cinema.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ScreeningRoomDTO {

    private Integer id;

    private String name;

    private Set<SeatDTO> seats;
}
