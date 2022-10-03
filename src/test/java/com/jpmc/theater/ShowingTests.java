package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowingTests {
    //Check is sequence function for showing.
    @Test
    void checkIsSequence() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(showing.isSequence(1));
        assertFalse(showing.isSequence(3));
    }

    //Check calculationFee function with discount for total cost
    @Test
    void checkCalculationFee() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 00))
        );
        assertEquals(showing.calculateFee(3), 28.125);
    }

    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(10, showing.calculateTicketPrice());
    }

    @Test
    void specialMovieWith3DollarDiscount() {
        Movie spiderManFirst = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 0);
        Showing showingFirst = new Showing(spiderManFirst, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(9.5, showingFirst.calculateTicketPrice());

    }

    @Test
    void specialMovieWith2DollarDiscount() {
        Movie spiderManFirst = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 0);
        Showing showingThird = new Showing(spiderManFirst, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(10.5,showingThird.calculateTicketPrice());
    }

    @Test
    void specialMovieWith1DollarDiscount() {
        Movie spiderManMock = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 0);
        Showing showing7th = new Showing(spiderManMock, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(11.5, showing7th.calculateTicketPrice());
    }

    @Test
    void specialMovieWith25PercentDiscount() {
        // Validate get 25% discount between 11:00 to 16:00, suppose include 11:00 am and 16:00 pm
        Movie spiderManTime = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1);
        Showing showingTime = new Showing(spiderManTime, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 00)));
        assertEquals(9.375, showingTime.calculateTicketPrice());
    }
}
