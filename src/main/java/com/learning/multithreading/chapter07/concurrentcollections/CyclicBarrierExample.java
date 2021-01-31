package com.learning.multithreading.chapter07.concurrentcollections;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class CyclicWorker implements Runnable {

    private int id;
    private CyclicBarrier cyclicBarrier;
    private final Random random;

    public CyclicWorker(int id, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.id = id;
        this.random = new Random();
    }

    public void run() {
        doWork();
    }

    public void doWork() {
        try {
            System.out.println("Thread with ID " + this.id + " starts working and at count...");
            Thread.sleep(this.random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            cyclicBarrier.await();
            System.out.println("After await...");
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()-> System.out.println("Task Finished.."));
        IntStream.range(1,6).forEach(i-> executorService.execute(new CyclicWorker(i, cyclicBarrier)));
    }
}
