package com.jpmc.theater;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing {
    // Special Discount
    private static final int MOVIE_CODE_SPECIAL = 1;
    // Sequence Discount
    private static final int FIRST_DISCOUNT = 1;
    private static final int SECOND_DISCOUNT = 2;
    private static final int SEVENTH_DISCOUNT = 7;
    //Discount Amount
    private static final int TREE_DOLLOR_DISCOUNT = 3;
    private static final int TWO_DOLLOR_DISCOUNT = 2;
    private static final int ONE_DOLLOR_DISCOUNT = 1;

    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public LocalDateTime getStartTime() {
        return this.showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return getMovie().getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return this.sequenceOfTheDay;
    }

    private double getDiscount() {
        double specialDiscount = 0;
        if (getMovie().getSpecialCode() == MOVIE_CODE_SPECIAL) {
            specialDiscount = getMovieFee() * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (this.getSequenceOfTheDay() == FIRST_DISCOUNT) {
            sequenceDiscount = TREE_DOLLOR_DISCOUNT; // $3 discount for 1st show
        } else if (this.getSequenceOfTheDay() == SECOND_DISCOUNT) {
            sequenceDiscount = TWO_DOLLOR_DISCOUNT; // $2 discount for 2nd show
        } else if (this.getSequenceOfTheDay() == SEVENTH_DISCOUNT) {
            sequenceDiscount = ONE_DOLLOR_DISCOUNT;
        }

        // 25% discount during 11:00  to 16:00, I suppose include 11:00 am and 16:00 am.
        double timeDiscount = 0.0;
        if (this.getStartTime().equals(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)))
                || this.getStartTime().equals(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)))
                || (this.getStartTime().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)))
                && this.getStartTime().isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))))) {
            timeDiscount = getMovieFee() * 0.25;
        }
        // Even there is no discount customer should be able to pay the ticket prices.
        // biggest discount wins
        return Math.max(timeDiscount, Math.max(sequenceDiscount, specialDiscount));
    }

    public double calculateTicketPrice() {
        return getMovieFee() - getDiscount();
    }

    public double calculateFee(int audienceCount) {
        return calculateTicketPrice() * audienceCount;
    }
}
