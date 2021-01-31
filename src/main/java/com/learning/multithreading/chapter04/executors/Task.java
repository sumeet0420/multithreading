package com.learning.multithreading.chapter04.executors;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        long timeout = (long) Math.random() * 5;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException ignored) { }
        System.out.println("Running the task "+id+" for thread "+Thread.currentThread().getName());
    }
}