package com.hotel.reservation.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;
import com.hotel.reservation.repos.GuestRepository;
import com.hotel.reservation.repos.ReservationRepository;
import com.hotel.reservation.service.ReservationService;
import com.hotel.reservation.util.customException.ReservationAlreadyExistsException;
import com.hotel.reservation.util.customException.ReservationNotFoundException;

import jakarta.persistence.EntityManager;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	
    @Autowired
    private ReservationRepository reservationRepo;
    
    @Autowired
    private GuestRepository guestRepo;
    
    @Autowired
    private EntityManager entityManager;


    @Override
    public Reservation createReservation(Reservation reservation) {
        List<Guest> guests = reservation.getGuests();
        List<String> guestEmails = guests.stream().map(Guest::getEmail).collect(Collectors.toList());

        //Checking if for the same customer if already reservation is there for same dates
        List<Reservation> existingReservations = reservationRepo.findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(guestEmails, reservation.getCheckin(), reservation.getCheckout());

        if (!existingReservations.isEmpty()) {
            throw new ReservationAlreadyExistsException("Reservation already exists for the selected dates and guests");
        }

        //Assuming that a guest can be returning guest so not creating new
        guests.forEach(guest -> {
        	//Checking if guest already present in our DB fetching by email id and setting id otherwise creating new guest
        	Guest guestTemp = guestRepo.findTopByEmail(guest.getEmail());
        	if(guestTemp != null) {
        		guest.setId(guestTemp.getId());
        	} else {
        		guest.setId(guestRepo.save(guest).getId());
        	}
        });
        reservation.setGuests(guests);
        
        return reservationRepo.save(reservation);
    }

    @Override
	public Reservation updateReservation(long id, Reservation reservation) {
		 Optional<Reservation> existingReservation = reservationRepo.findById(id);
	        if (!existingReservation.isPresent()) {
	            throw new ReservationNotFoundException(id);
	        }

	        List<Guest> guests = reservation.getGuests();
	        List<String> guestEmails = guests.stream().map(Guest::getEmail).collect(Collectors.toList());
	        //Checking if for the same customer if already reservation is there for same dates
	        List<Reservation> existingReservations = reservationRepo.findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqualAndIdNotIn(guestEmails, reservation.getCheckin(), reservation.getCheckout(),List.of(id));
	        
	        if (!existingReservations.isEmpty()) {
	            throw new ReservationAlreadyExistsException("Reservation already exists for the selected dates and guests");
	        }
	        
	        Reservation updatedReservation = existingReservation.get();
	        updatedReservation.setCheckin(reservation.getCheckin());
	        updatedReservation.setCheckout(reservation.getCheckout());
	        
	        
	        //Assuming that a guest can be returning guest so not creating new
	        guests.forEach(guest -> {
	        	//Checking if guest already present in our DB fetching by email id and setting id otherwise creating new guest
	        	Guest guestTemp = guestRepo.findTopByEmail(guest.getEmail());
	        	if(guestTemp != null) {
	        		guest.setId(guestTemp.getId());
	        	} else {
	        		guest.setId(guestRepo.save(guest).getId());
	        	}
	        });
	        
	        updatedReservation.setGuests(guests);

	        
	        return reservationRepo.save(updatedReservation);
	}
    @Override
    public Optional<Reservation> findReservationById(Long id) {
    	Optional<Reservation> reservation = reservationRepo.findById(id);
    	if (!reservation.isPresent()) {
            throw new ReservationNotFoundException(id);
        }
        return reservation;
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepo.findAll();
    }



    @Override
    public void deleteReservation(Long id) {
        reservationRepo.deleteById(id);
    }

    @Override
    public boolean isReserved(LocalDate checkin, LocalDate checkout, Guest guest) {
        List<Reservation> reservations = reservationRepo.findByGuestsContainingAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(guest,checkin,checkout);
        if(reservations.size() > 0)
        	return true;
        return false;
    }
    
    

    @Override
	public List<Reservation> getReservations(LocalDate start, LocalDate end) {
		return reservationRepo.findByCheckinGreaterThanEqualAndCheckoutLessThanEqual(start, end);
	}
}

