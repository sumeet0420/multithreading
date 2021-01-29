package com.learning.multithreading.chapter03.multithreading;

public class VolatileUsage implements Runnable{

    private volatile boolean terminated = false;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Program is running");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileUsage volatileUsage = new VolatileUsage();
        Thread t1 = new Thread(volatileUsage);
        t1.start();
        Thread.sleep(1000);
        volatileUsage.terminated = true;
        System.out.println("Program over");
    }
}
