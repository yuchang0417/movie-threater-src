package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    private static Theater theater;

    @BeforeAll
    public static void init() {
        theater = new Theater(LocalDateProvider.singleton());
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "With Spider-Man's identity now revealed,", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", "A thirteen-year-old girl is torn between staying her mother's dutiful daughter and the changes of adolescence.", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues.", Duration.ofMinutes(95), 9, 0);
        theater.setSchedule(
                List.of(
                        new Showing(turningRed, 1, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(9, 0))),
                        new Showing(spiderMan, 2, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(11, 0))),
                        new Showing(theBatMan, 3, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(12, 50))),
                        new Showing(turningRed, 4, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(14, 30))),
                        new Showing(spiderMan, 5, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(16, 10))),
                        new Showing(theBatMan, 6, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(17, 50))),
                        new Showing(turningRed, 7, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(19, 30))),
                        new Showing(spiderMan, 8, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(21, 10))),
                        new Showing(theBatMan, 9, LocalDateTime.of(theater.getProvider().currentDate(), LocalTime.of(23, 0)))
                )
        );
    }

    @Test
    void totalFeeForCustomer() {
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        // The true payment should be price after discount.
        assertEquals(reservation.getTotalFeeBeforeDiscount(), 50);
        // We should calculate the price with movie discount. Discount should be 25% discount between 11:00 to 16:00
        assertEquals(reservation.getTotalFeeAfterDiscount(), 37.5);
    }

    @Test
    void printMovieSchedule() {
        theater.printSchedule();
        theater.printScheduleInJson();
    }
}