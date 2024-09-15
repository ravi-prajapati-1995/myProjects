package com.hotel.reservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.hotel.reservation.domain.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {
	Guest findTopByEmail(String email);
}
