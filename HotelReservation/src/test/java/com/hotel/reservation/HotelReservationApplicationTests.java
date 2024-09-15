package com.hotel.reservation;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotel.reservation.controller.ReservationController;
import com.hotel.reservation.domain.Guest;
import com.hotel.reservation.domain.Reservation;
import com.hotel.reservation.service.ReservationService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HotelReservationApplicationTests {

	@Autowired
	private ReservationController reservationController;
	
	private MockMvc mockMvc;
	
	@Mock
	private ReservationService reservationService;
	
	@BeforeAll
    public  void setup() {
       mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }
	 
    @Test
    public void testExample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservations/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$.id", is(1)))
               ;
    }
	   
    @Test
    public void testCreateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(1));

        Guest guest = new Guest();
        guest.setEmail("ravikumar1@gmail.com");
        guest.setName("Ravi Kumar");
        reservation.setGuests(Arrays.asList(guest));
        

        when(reservationService.createReservation(reservation)).thenReturn(reservation);
       
        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().is2xxSuccessful());
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
