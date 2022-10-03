package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double getTotalFeeBeforeDiscount() {
        return showing.getMovieFee() * audienceCount;
    }

    public double getTotalFeeAfterDiscount() {
        return showing.calculateFee(audienceCount);
    }
}