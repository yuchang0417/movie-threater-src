package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Duration getRunningTime() {
        return this.runningTime;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public int getSpecialCode() {
        return this.specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.getTicketPrice(), getTicketPrice()) == 0
                && Objects.equals(getTitle(), movie.getTitle())
                && Objects.equals(getDescription(), movie.getDescription())
                && Objects.equals(getRunningTime(), movie.getRunningTime())
                && Objects.equals(getSpecialCode(), movie.getSpecialCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getRunningTime(), getTicketPrice(), getSpecialCode());
    }
}