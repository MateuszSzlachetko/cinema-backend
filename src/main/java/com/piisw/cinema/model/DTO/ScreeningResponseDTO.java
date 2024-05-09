package com.piisw.cinema.model.DTO;

import com.piisw.cinema.model.entity.Screening;
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
public class ScreeningResponseDTO {

    private UUID id;

    private LocalDateTime startDate;

    private Integer advertisementsDuration;

    private UUID movieId;

    private Integer screeningRoomId;

    public static ScreeningResponseDTO map(Screening screening) {
        return ScreeningResponseDTO.builder()
                .id(screening.getId())
                .startDate(screening.getStartDate())
                .advertisementsDuration(screening.getAdvertisementsDuration())
                .movieId(screening.getMovie().getId())
                .screeningRoomId(screening.getScreeningRoom().getId())
                .build();
    }
}
