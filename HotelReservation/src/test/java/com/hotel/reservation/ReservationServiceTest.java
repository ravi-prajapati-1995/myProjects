package com.hotel.reservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;
import com.hotel.reservation.repos.GuestRepository;
import com.hotel.reservation.repos.ReservationRepository;
import com.hotel.reservation.service.Impl.ReservationServiceImpl;
import com.hotel.reservation.util.customException.ReservationAlreadyExistsException;


@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

  @Mock
  private ReservationRepository reservationRepository;
  
  @Mock
  private GuestRepository guestRepository;

  @InjectMocks
  private ReservationServiceImpl reservationService;

  
  @Test
  public void testCreateReservation(){
    Reservation reservation = createReservation();
    
    when(reservationRepository.findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(Mockito.anyList(), any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(List.of());
    Guest guest = reservation.getGuests().get(0);
    
    when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
    when(guestRepository.save(guest)).thenReturn(guest);

    reservationService.createReservation(reservation);

    verify(reservationRepository, times(1)).findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(any(), any(), any());
    verify(reservationRepository, times(1)).save(any(Reservation.class));
    verify(guestRepository, times(1)).save(any(Guest.class));
  }
  
  @Test
  public void testCreateReservationAlreadyExists(){
    // given
    Reservation reservation = createReservation();
    
    when(reservationRepository.findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(any(), any(), any()))
        .thenReturn(List.of(reservation));

    assertThrows(ReservationAlreadyExistsException.class, ()->reservationService.createReservation(reservation));
  }
  
  @Test
  public void createReservation_ShouldSaveReservationToRepository() {
    Reservation reservation = createReservation();
    
    when(reservationRepository.findByGuests_EmailInAndCheckinGreaterThanEqualAndCheckoutLessThanEqual(Mockito.anyList(), any(LocalDate.class), any(LocalDate.class)))
    .thenReturn(List.of());
    Guest guest = reservation.getGuests().get(0);
    when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
    when(guestRepository.save(guest)).thenReturn(guest);
  
    reservationService.createReservation(reservation);
   
    verify(reservationRepository, times(1)).save(reservation);
  }



  @Test
  public void getReservations_ShouldReturnListOfReservationsFromRepository() {
    List<Reservation> reservations = Arrays.asList(createReservation(), createReservation());
    when(reservationRepository.findAll()).thenReturn(reservations);
    List<Reservation> result = reservationService.findAllReservations();
    assertEquals(reservations, result);
  }


  private Reservation createReservation() {
    Reservation reservation = new Reservation();
    reservation.setCheckin(LocalDate.of(2023, 02, 02));
    reservation.setCheckout(LocalDate.of(2022, 02, 10));
    Guest guest = new Guest();
    guest.setName("ravi Kumar");
    guest.setEmail("ravikumar@gmail.com");
    reservation.setGuests(Collections.singletonList(guest));
    
    return reservation;
  }

}

