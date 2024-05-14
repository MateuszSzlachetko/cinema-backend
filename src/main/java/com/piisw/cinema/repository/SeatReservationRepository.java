package com.piisw.cinema.repository;

import com.piisw.cinema.model.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, UUID> {
}
