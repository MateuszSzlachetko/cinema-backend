package com.piisw.cinema.repository;

import com.piisw.cinema.model.entity.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Integer> {

    Optional<ScreeningRoom> findScreeningRoomById(Integer id);
}
