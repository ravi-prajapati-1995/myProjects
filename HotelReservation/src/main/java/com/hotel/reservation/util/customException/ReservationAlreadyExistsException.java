package com.hotel.reservation.util.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Reservation already exists for the given guest")
public class ReservationAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1234567L;
    public ReservationAlreadyExistsException() {}
    public ReservationAlreadyExistsException(String message) {
        super(message);
    }
}
