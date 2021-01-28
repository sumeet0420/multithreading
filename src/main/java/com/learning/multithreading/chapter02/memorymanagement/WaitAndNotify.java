package com.learning.multithreading.chapter02.memorymanagement;

public class WaitAndNotify {

    public void produce() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Produce method is executed...");
            wait();
            System.out.println("Produce method is resuming...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume method is executed...");
            notify();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        new Thread(()->{
            try {
                waitAndNotify.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                waitAndNotify.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
