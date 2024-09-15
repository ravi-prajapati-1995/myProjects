package com.hotel.reservation.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(List<String> emails, LocalDate start, LocalDate end);
	List<Reservation> findByCheckinGreaterThanEqualAndCheckoutLessThanEqual(LocalDate start, LocalDate end);
	List<Reservation> findByGuestsContaining(Guest guest);
	List<Reservation> findByGuestsContainingAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(Guest guest, LocalDate start, LocalDate end);
	List<Reservation> findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqualAndIdNotIn(List<String> emails, LocalDate start, LocalDate end,List<Long> id);
}
