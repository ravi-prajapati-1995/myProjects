package com.hotel.reservation;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotel.reservation.controller.ReservationController;
import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;
import com.hotel.reservation.service.ReservationService;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {
  
	@Mock
    private WebApplicationContext wac;
	
    
    private MockMvc mockMvc;
  
    @Mock
    private ReservationService reservationService;
   
    @InjectMocks
    private ReservationController reservationController;
    
    @BeforeEach // For Junit5
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

    }
    

    @Test
    public void testCreateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumar@gmail.com");
        guest.setName("Ravi Kumar");
        reservation.setGuests(Arrays.asList(guest));

        lenient().when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testCreateReservation_invalidDate() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().minusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumar@gmail.com");
        guest.setName("Ravi Kumar");
        reservation.setGuests(Arrays.asList(guest));

        lenient().when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
        .andDo(print())
                .andExpect(status().is4xxClientError());
        		
    }
    
    @Test
    public void testCreateReservation_invalidName() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumar@gmail.com");
        guest.setName("");
        reservation.setGuests(Arrays.asList(guest));

        lenient().when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        MvcResult mvcResult = mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
        .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn()
                ;
        		
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
//        print(content);
    }
    
    @Test
    public void testCreateReservation_invalidEmailID() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumargmail.com");
        guest.setName("Ravi Kumar");
        reservation.setGuests(Arrays.asList(guest));

        lenient().when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
        .andDo(print())
                .andExpect(status().is4xxClientError());
        		
    }
    
    @Test
    public void testCreateReservation_withoutGuest() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));
        reservation.setGuests(List.of());

        lenient().when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
        .andDo(print())
                .andExpect(status().is4xxClientError());
        		
    }
    
    
    @Test
    public void testUpdateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumar@gmail.com");
        guest.setName("Ravi Kumar");
        reservation.setGuests(Arrays.asList(guest));

        lenient().when(reservationService.updateReservation(1l,reservation)).thenReturn(reservation);
       
        mockMvc.perform(put("/reservations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().is2xxSuccessful())
                ;
    }
    
    public void testUpdateReservation_withoutGuest() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        lenient().when(reservationService.updateReservation(1l,reservation)).thenReturn(reservation);
       
        mockMvc.perform(put("/reservations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().is4xxClientError())
                ;
    }
   
  
    public static String asJsonString(final Object obj) {
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   
    	  
}

