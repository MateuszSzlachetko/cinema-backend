package com.piisw.cinema.model.DTO;

import com.piisw.cinema.model.enums.Column;
import com.piisw.cinema.model.enums.Row;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class SeatDTO {
    private Integer id;

    private Row row;

    private Column column;

    private Boolean isFree;
}
