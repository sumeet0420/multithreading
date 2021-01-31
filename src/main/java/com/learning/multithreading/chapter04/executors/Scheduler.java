package com.learning.multithreading.chapter04.executors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Database implements Runnable {

    @Override
    public void run() {
        LocalDateTime endTime = LocalDateTime.now().plusSeconds(9);
        while (endTime.isAfter(LocalDateTime.now())) {
            System.out.println("Executing query");
        }
    }
}


public class Scheduler {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        IntStream.range(0, 5).forEach(i -> scheduledExecutorService.scheduleAtFixedRate(new Database(), 1000, 5000, TimeUnit.MILLISECONDS));
        Thread.sleep(2000);
        scheduledExecutorService.shutdown();
    }
}
