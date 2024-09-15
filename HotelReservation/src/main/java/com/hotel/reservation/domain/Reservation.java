package com.hotel.reservation.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @FutureOrPresent
    private LocalDate checkin;

    @NotNull
    @Future
    private LocalDate checkout;

    //A guest can Have multiple reservations and A reservations can have multiple Guests so using manytoMany
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Valid
    @Size(min = 1)
    private List<Guest> guests;

    public Reservation() {}

    public Reservation(LocalDate checkin, LocalDate checkout, List<Guest> guests) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.guests = guests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    
    //This method is used to check if checkout date is after checkin date
    @AssertTrue(message = "check out date should be after check in date")
    public boolean isValidDateRange() {
      return checkout.isAfter(checkin);
    }
}
