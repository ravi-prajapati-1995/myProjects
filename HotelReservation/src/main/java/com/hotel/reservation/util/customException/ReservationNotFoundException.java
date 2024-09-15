package com.hotel.reservation.util.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Reservation not found")
public class ReservationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReservationNotFoundException() {}
    public ReservationNotFoundException(long id) {
        super("Reservation with ID " + id + " not found");
    }
}
