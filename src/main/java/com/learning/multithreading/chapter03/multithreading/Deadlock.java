package com.learning.multithreading.chapter03.multithreading;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public void worker1() {
        lock1.lock();
        System.out.println("Worker 1 has acquired lock1");
        sleep( 500, TimeUnit.MILLISECONDS);
        lock2.lock();
        System.out.println("Worker 1 has acquired lock2");
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock1.lock();
        System.out.println("Worker 2 has acquired lock1");
        lock2.lock();
        System.out.println("Worker 2 has acquired lock2");
        lock1.unlock();
        lock2.unlock();
    }

    //This will endup with deadlock
    public void worker2Deadlock() {
        lock2.lock();
        System.out.println("Worker 2 has acquired lock2");
        lock1.lock();
        System.out.println("Worker 2 has acquired lock1");
        lock1.unlock();
        lock2.unlock();
    }

    private static void sleep(long milliseconds, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        executeQuery(1, ChronoUnit.SECONDS, 5, TimeUnit.SECONDS);
        Deadlock deadlock = new Deadlock();
        new Thread(deadlock::worker1).start();
//        new Thread(deadlock::worker2Deadlock).start();
        new Thread(deadlock::worker2).start();
    }

    public static void executeQuery(long polling, ChronoUnit pollingUnitChrono, long timeout, TimeUnit timeoutUnit) {
        LocalDateTime endTime = LocalDateTime.now().plus(timeout, pollingUnitChrono);
        while (LocalDateTime.now().isBefore(endTime)) {
            sleep(polling, timeoutUnit);
            System.out.println("Before Timeout");
        }
    }
}
