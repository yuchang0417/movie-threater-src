package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;

    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
    }

    public List<Showing> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Showing> schedule) {
        this.schedule = schedule;
    }

    public LocalDateProvider getProvider() {
        return this.provider;
    }


    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = getSchedule().get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(getProvider().currentDate());
        System.out.println("===================================================");
        getSchedule().forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    public void printScheduleInJson() {
        System.out.println(getProvider().currentDate());
        System.out.println("===================================================");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String scheduleJson = gson.toJson(getSchedule());
        System.out.println(scheduleJson);
        System.out.println("===================================================");
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }
}