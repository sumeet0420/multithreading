package com.learning.multithreading.chapter00.sleeps;

import java.util.concurrent.TimeUnit;

public class TimeUnitSleep {

    public static void main(String[] args) throws InterruptedException {
        sleep(5, TimeUnit.SECONDS);
        sleep(500, TimeUnit.MILLISECONDS);
    }

    private static void sleep(long timeToWait, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
