package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFeeBeforeDiscount() == 37.5);
        //All the tickets will get 3 dollar discount since sequence is 1st.
        assertEquals(new Reservation(customer, showing,3).getTotalFeeAfterDiscount(),  28.5);
    }
}

