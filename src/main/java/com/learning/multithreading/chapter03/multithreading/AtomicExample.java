package com.learning.multithreading.chapter03.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicExample {

    private AtomicInteger counterAtomic = new AtomicInteger(0);
    private int counter = 0;

    public void atomicIncrement() {
        IntStream.range(0, 10000).forEach(i -> counterAtomic.getAndIncrement());
    }

    public void increment() {
        IntStream.range(0, 100000).forEach(i -> counter++);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicExample atomicExample = new AtomicExample();
        Thread t1 = new Thread(atomicExample::atomicIncrement);
        t1.start();
        Thread t2 = new Thread(atomicExample::atomicIncrement);
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicExample.counterAtomic);

        AtomicExample atomicExample2 = new AtomicExample();
        Thread t3 = new Thread(atomicExample::increment);
        t3.start();
        Thread t4 = new Thread(atomicExample::increment);
        t4.start();
        t3.join();
        t4.join();
        System.out.println(atomicExample.counter);
    }
}
