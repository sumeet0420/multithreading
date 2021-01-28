package com.learning.multithreading.chapter02.memorymanagement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Locks {

    private static Lock lock = new ReentrantLock();
    private static int counter = 0;

    public static void increment() {
        IntStream.range(0,10000).forEach(i-> {
            try {
                lock.lock();
                counter++;
            }finally {
            lock.unlock();
            }
        });
    }

     public static void main(String[] args) throws InterruptedException {
         Thread t1 = new Thread(() -> increment());
         Thread t2 = new Thread(() -> increment());
         t1.start();
         t2.start();
         t1.join();
         t2.join();
         System.out.println("Counter: "+counter);
     }
}
