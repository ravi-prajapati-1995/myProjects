package com.hotel.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.service.GuestService;

@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/guests")
    public ResponseEntity<Iterable<Guest>> getAllGuests() {
        Iterable<Guest> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/guests/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        Guest guest = guestService.getGuestById(id);
        return ResponseEntity.ok(guest);
    }

    @PutMapping("/guests/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(id, guest);
        return ResponseEntity.ok(updatedGuest);
    }
}

