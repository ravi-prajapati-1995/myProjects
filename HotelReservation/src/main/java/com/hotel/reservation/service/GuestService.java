package com.hotel.reservation.service;

import com.hotel.reservation.domain.Guest;

public interface GuestService {

	Guest createGuest(Guest guest);
	Iterable<Guest> getAllGuests();
	Guest getGuestById(Long id);
	Guest updateGuest(Long id, Guest guest);
}
