package com.hotel.reservation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reservation.domain.Reservation;
import com.hotel.reservation.service.ReservationService;
import com.hotel.reservation.util.customException.ReservationAlreadyExistsException;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) throws ReservationAlreadyExistsException {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable long id, @Valid @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @GetMapping
    public List<Reservation> getReservations(@RequestParam(required = false) LocalDate start, @RequestParam(required = false) LocalDate end) {
        return reservationService.getReservations(start, end);
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable long id) {
        return reservationService.findReservationById(id);
    }
}

