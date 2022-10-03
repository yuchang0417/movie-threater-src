package com.jpmc.theater;

import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return make sure to return singleton instance
     */
    // Use synchronization technique to guarantee atomicity operation
    public synchronized static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }

    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
