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
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final ScreeningRepository screeningRepository;
    private final UserRepository userRepository;

    public List<Ticket> getTicketsByScreeningId(UUID screeningId) {
        return this.ticketRepository.findTicketsByScreeningId(screeningId);
    }

    public PurchaseTicketResponseDTO purchaseTicket(PurchaseTicketRequestDTO purchaseTicketRequest) {
        // Szukamy usera o takim id
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Pobranie seansu na podstawie id
        Screening screening = screeningRepository.findById(purchaseTicketRequest.getScreeningId())
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono seansu o id: " + purchaseTicketRequest.getScreeningId()));

        // Pobranie sali kinowej dla seansu
        ScreeningRoom screeningRoom = screening.getScreeningRoom();

        // Pobranie wszystkich biletów na dany seans
        Set<Ticket> tickets = new HashSet<>(this.getTicketsByScreeningId(purchaseTicketRequest.getScreeningId()));
        System.out.println(tickets);

        // Pobranie zarezerwowanych miejsc dla danego seansu
        Set<SeatReservation> seatReservations = tickets.stream()
                .map(Ticket::getReservatedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());


        // Sprawdzenie, czy wybrane przez uzytkownika miejsca nie sa juz zajete
        if (purchaseTicketRequest.getSeats().stream().anyMatch(seat -> seatReservations.stream().anyMatch(reservation -> reservation.getSeat().getId().equals(seat.getId())))) {
            throw new IllegalStateException("Wybrane miejsce jest już zajęte.");
        }


        // Tworzenie unikatowego kodu rezerwacji w zależności od daty rezerwacji
        String dataToHash = LocalDateTime.now().toString() + UUID.randomUUID().toString();
        UUID reservationCode = UUID.nameUUIDFromBytes(dataToHash.getBytes());

        // Tworzymy nowy ticket
        Ticket ticket = Ticket.builder()
                .purchaseDate(LocalDateTime.now())
                .reservationCode(reservationCode)
                .state(TicketState.INVALID)
                .customer(user)
                .screening(screening)
                .reservatedSeats(new HashSet<>())
                .build();

        // Tworzenie rezerwacji miejsc dla biletu
        for (Seat seat : purchaseTicketRequest.getSeats()) {
            ticket.getReservatedSeats().add(SeatReservation.builder().ticket(ticket).seat(seat).build());
        }

        ticketRepository.save(ticket);

        PurchaseTicketResponseDTO ticketResponseDTO = PurchaseTicketResponseDTO.builder()
                .purchaseDate(ticket.getPurchaseDate())
                .reservationCode(ticket.getReservationCode())
                .screeningId(ticket.getScreening().getId())
                .movieTitle(ticket.getScreening().getMovie().getTitle())
                .build();

        return ticketResponseDTO;
    }

    public CheckTicketDTO checkTicket(UUID ticketId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Szukamy biletu o takim id
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono biletu o ID: " + ticketId));

        LocalDateTime movieStartTime = ticket.getScreening().getStartDate();
        LocalDateTime movieEndTime = movieStartTime.plusMinutes(ticket.getScreening().getAdvertisementsDuration() + ticket.getScreening().getMovie().getDuration());

        if (user.getUserType().equals(UserType.USHER)) {
            if (ChronoUnit.MINUTES.between(LocalDateTime.now(), movieStartTime) > 15) {
                return createCheckTicketDTO(ticket, "Nie można jeszcze skasować biletu - do rozpoczęcia seansu zostało więcej niż 15 minut.");
            } else if (LocalDateTime.now().isAfter(movieEndTime) && !ticket.getState().equals(TicketState.VALIDATED)) {
                ticket.setState(TicketState.INVALID);
                ticketRepository.save(ticket);
                return createCheckTicketDTO(ticket, "Bilet jest już nieważny.");
            } else if (ticket.getState().equals(TicketState.VALIDATED)) {
                return createCheckTicketDTO(ticket, "Bilet został już sprawdzony");
            } else {
                ticket.setState(TicketState.VALIDATED);
                ticketRepository.save(ticket);
                return createCheckTicketDTO(ticket, "Bilet pomyślnie sprawdzony. Życzymy miłego seansu.");
            }
        } else return createCheckTicketDTO(ticket, "Brak uprawnień do sprawdzenia biletu");

    }

    private CheckTicketDTO createCheckTicketDTO(Ticket ticket, String checkMessage) {
        return CheckTicketDTO.builder()
                .purchaseDate(ticket.getPurchaseDate())
                .ticketState(ticket.getState())
                .customerId(ticket.getCustomer().getId())
                .reservationCode(ticket.getReservationCode())
                .screeningId(ticket.getScreening().getId())
                .movieTitle(ticket.getScreening().getMovie().getTitle())
                .checkMessage(checkMessage)
                .build();
    }

}
