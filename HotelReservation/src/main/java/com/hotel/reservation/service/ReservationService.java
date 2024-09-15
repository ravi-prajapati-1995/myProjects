package com.hotel.reservation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;


public interface ReservationService {
	
	Reservation createReservation(Reservation reservation);
	Reservation updateReservation(long id,Reservation reservation);
    Optional<Reservation> findReservationById(Long id);
    List<Reservation> findAllReservations();
    List<Reservation> getReservations(LocalDate start,LocalDate end);
    void deleteReservation(Long id);
    boolean isReserved(LocalDate checkin, LocalDate checkout, Guest guest);
}

