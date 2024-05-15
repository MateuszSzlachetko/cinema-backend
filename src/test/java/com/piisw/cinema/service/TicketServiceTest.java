package com.piisw.cinema.service;

import com.piisw.cinema.model.DTO.CheckTicketDTO;
import com.piisw.cinema.model.DTO.PurchaseTicketRequestDTO;
import com.piisw.cinema.model.DTO.PurchaseTicketResponseDTO;
import com.piisw.cinema.model.entity.*;
import com.piisw.cinema.model.enums.TicketState;
import com.piisw.cinema.model.enums.UserType;
import com.piisw.cinema.repository.ScreeningRepository;
import com.piisw.cinema.repository.SeatReservationRepository;
import com.piisw.cinema.repository.TicketRepository;
import com.piisw.cinema.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.random.RandomGenerator;

import static com.piisw.cinema.model.enums.UserType.VIEWER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SeatReservationRepository seatReservationRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testPurchaseTicket_Success() {
        // Mocking user details
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserType(VIEWER);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        // Mocking screening details
        Screening screening = new Screening();
        screening.setId(UUID.randomUUID());
        screening.setStartDate(LocalDateTime.now().plusDays(1));
        screening.setScreeningRoom(new ScreeningRoom());

        // Mocking movie details
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        screening.setMovie(movie);

        when(screeningRepository.findById(any(UUID.class))).thenReturn(Optional.of(screening));

        // Mocking seat details
        Seat seat = new Seat();
        seat.setId(RandomGenerator.getDefault().nextInt(1,100));
        List<Seat> seats = Collections.singletonList(seat);

        // Mocking request DTO
        PurchaseTicketRequestDTO requestDTO = new PurchaseTicketRequestDTO(screening.getId(),seats);
        requestDTO.setScreeningId(screening.getId());
        requestDTO.setSeats(seats);

        // Mocking ticket repository
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call service method
        PurchaseTicketResponseDTO responseDTO = ticketService.purchaseTicket(requestDTO);

        // Assertions
        assertNotNull(responseDTO);
        assertEquals(screening.getId(), responseDTO.getScreeningId());
        assertEquals("Test Movie", responseDTO.getMovieTitle());
    }

    @Test
    void testCheckTicket_Success() {
        // Mocking user details
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserType(UserType.USHER);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        // Mocking ticket details
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setState(TicketState.INVALID);
        ticket.setCustomer(user);

        Screening screening = new Screening();
        screening.setStartDate(LocalDateTime.now().plusMinutes(10));
        screening.setAdvertisementsDuration(10);
        screening.setMovie(new Movie());
        screening.getMovie().setDuration(120);

        ticket.setScreening(screening);

        when(ticketRepository.findById(any(UUID.class))).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call service method
        CheckTicketDTO checkTicketDTO = ticketService.checkTicket(ticket.getId());

        // Assertions
        assertNotNull(checkTicketDTO);
        assertEquals(TicketState.VALIDATED, checkTicketDTO.getTicketState());
    }

    @Test
    void testCheckTicket_NoPermission() {
        // Mocking user details
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserType(VIEWER);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        // Mocking ticket details
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setState(TicketState.INVALID);
        ticket.setCustomer(user);

        Screening screening = new Screening();
        screening.setStartDate(LocalDateTime.now().plusMinutes(10));
        screening.setAdvertisementsDuration(10);

        Movie movie = new Movie();
        movie.setDuration(120);
        movie.setTitle("Test Movie");

        screening.setMovie(movie);

        ticket.setScreening(screening);

        when(ticketRepository.findById(any(UUID.class))).thenReturn(Optional.of(ticket));

        // Call service method
        CheckTicketDTO checkTicketDTO = ticketService.checkTicket(ticket.getId());

        // Assertions
        assertNotNull(checkTicketDTO);
        assertEquals(TicketState.INVALID, checkTicketDTO.getTicketState());
        assertEquals("Brak uprawnień do sprawdzenia biletu", checkTicketDTO.getCheckMessage());
    }

    @Test
    void testCheckTicket_TicketNotFound() {
        // Mocking user details
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserType(UserType.USHER);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        when(ticketRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Assertions
        assertThrows(NoSuchElementException.class, () -> ticketService.checkTicket(UUID.randomUUID()));
    }
}