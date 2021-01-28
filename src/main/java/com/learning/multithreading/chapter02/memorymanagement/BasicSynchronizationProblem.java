package com.learning.multithreading.chapter02.memorymanagement;

public class BasicSynchronizationProblem {

    private static int counter_1 = 0;
    private static int counter_2 = 0;

    private synchronized static void increment1() {
        counter_1++;
    }

    private synchronized static void increment2() {
        counter_2++;
    }
    public static void startMultiThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                //System.out.println("i from thread 1 with value "+i +" and counter "+counter);
                increment1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 150; i++) {
                //System.out.println("i from thread 2 with value "+i +" and counter "+counter);
                increment2();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter 1: final value is ::" + counter_1);
        System.out.println("counter 2: final value is ::" + counter_2);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new BasicSynchronizationProblem().startMultiThread();
        }
        System.out.println("*".repeat(30) + "Execution completed" + "*".repeat(30));
    }
}
