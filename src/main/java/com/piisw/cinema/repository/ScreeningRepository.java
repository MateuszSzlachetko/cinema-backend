package com.piisw.cinema.repository;

import com.piisw.cinema.model.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, UUID> {

    List<Screening> findScreeningsByStartDateBetween(LocalDateTime startDate,LocalDateTime endDate);

//    Optional<Screening> findById(UUID id);
}
