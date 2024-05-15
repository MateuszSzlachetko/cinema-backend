package com.piisw.cinema.service;

import com.piisw.cinema.model.DTO.ScreeningRoomDTO;
import com.piisw.cinema.model.DTO.SeatDTO;
import com.piisw.cinema.model.entity.*;
import com.piisw.cinema.model.enums.Column;
import com.piisw.cinema.model.enums.Row;
import com.piisw.cinema.repository.ScreeningRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScreeningRoomServiceTest {

    @Mock
    private ScreeningRoomRepository screeningRoomRepository;

    @Mock
    private ScreeningService screeningService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private ScreeningRoomService screeningRoomService;

    private UUID screeningId;
    private Screening screening;
    private ScreeningRoom screeningRoom;
    private Set<Ticket> tickets;
    private Set<Seat> seats;
    private Set<SeatReservation> seatReservations;

    @BeforeEach
    public void setUp() {
        screeningId = UUID.randomUUID();

        screeningRoom = new ScreeningRoom();
        screeningRoom.setId(RandomGenerator.getDefault().nextInt(1,100));
        screeningRoom.setName("Screening Room 1");

        seats = new LinkedHashSet<>();
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat();
            seat.setId(RandomGenerator.getDefault().nextInt(1,100));
            seat.setRow(Row.A);
            seat.setColumn(Column.values()[i]);
            seats.add(seat);
        }
        screeningRoom.setSeats(seats);

        screening = new Screening();
        screening.setId(screeningId);
        screening.setScreeningRoom(screeningRoom);

        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());

        seatReservations = new HashSet<>();
        SeatReservation seatReservation = new SeatReservation();
        seatReservation.setSeat(seats.iterator().next());
        seatReservations.add(seatReservation);

        ticket.setReservatedSeats(seatReservations);
        tickets = new HashSet<>();
        tickets.add(ticket);
    }

    @Test
    public void testGetScreeningRoomByScreeningId_Success() {
        when(screeningService.getScreeningById(screeningId)).thenReturn(Optional.of(screening));
        when(ticketService.getTicketsByScreeningId(screeningId)).thenReturn(new ArrayList<>(tickets));

        ScreeningRoomDTO screeningRoomDTO = screeningRoomService.getScreeningRoomByScreeningId(screeningId);

        assertNotNull(screeningRoomDTO);
        assertEquals(screeningRoom.getId(), screeningRoomDTO.getId());
        assertEquals(screeningRoom.getName(), screeningRoomDTO.getName());
        assertEquals(seats.size(), screeningRoomDTO.getSeats().size());

        for (SeatDTO seatDTO : screeningRoomDTO.getSeats()) {
            if (seatReservations.stream().anyMatch(reservation -> reservation.getSeat().getId().equals(seatDTO.getId()))) {
                assertFalse(seatDTO.getIsFree());
            } else {
                assertTrue(seatDTO.getIsFree());
            }
        }
    }

    @Test
    public void testGetScreeningRoomByScreeningId_ScreeningNotFound() {
        when(screeningService.getScreeningById(screeningId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            screeningRoomService.getScreeningRoomByScreeningId(screeningId);
        });

        assertEquals("Nie znaleziono seansu z takim id", exception.getMessage());
    }

}
