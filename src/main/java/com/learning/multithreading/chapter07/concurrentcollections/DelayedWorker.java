package com.learning.multithreading.chapter07.concurrentcollections;

import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedWorker implements Delayed {

    private String message;
    private Instant instant;

    public DelayedWorker(long durationToAdd, String message) {
        this.instant = Instant.now().plusMillis(durationToAdd);
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return instant.minusMillis(System.currentTimeMillis()).toEpochMilli();
    }

    @Override
    public int compareTo(Delayed o) {
        return instant.compareTo(((DelayedWorker) o).instant);
    }

    @Override
    public String toString() {
        return "DelayedWorker{" +
                "instant=" + instant +
                ", message='" + message + '\'' +
                '}';
    }
}

class App {

    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
        try {
            queue.put(new DelayedWorker(1000, "First Worker"));
            queue.put(new DelayedWorker(10000, "Second Worker"));
            queue.put(new DelayedWorker(4000, "Third Worker"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}