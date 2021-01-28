package com.learning.multithreading.chapter02.memorymanagement;

public class BasicCounterSynchronized {

    private static int counter = 0;

    private synchronized void increment() {
        counter++;
    }
    public void startMultiThread() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                //System.out.println("i from thread 1 with value "+i +" and counter "+counter);
                increment();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 150; i++) {
                //System.out.println("i from thread 2 with value "+i +" and counter "+counter);
                increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter: final value is ::"+counter);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
        new BasicCounterSynchronized().startMultiThread();
        }
        System.out.println("*".repeat(30)+"Execution completed"+"*".repeat(30));
    }
}
