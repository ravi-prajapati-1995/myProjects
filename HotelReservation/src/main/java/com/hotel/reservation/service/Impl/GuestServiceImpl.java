package com.hotel.reservation.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.repos.GuestRepository;
import com.hotel.reservation.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {
	
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Iterable<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id).orElse(null);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = guestRepository.findById(id).orElse(null);
        if (existingGuest == null) {
            return null;
        }
        existingGuest.setName(guest.getName());
        existingGuest.setEmail(guest.getEmail());
        return guestRepository.save(existingGuest);
    }
}

