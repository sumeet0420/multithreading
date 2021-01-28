package com.learning.multithreading.chapter02.memorymanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProducerConsumer {

    private static List<Integer> list = new ArrayList<>();
    private int value = 0;
    private static final int UPPER_LIMIT = 7;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items");
                    lock.wait();
                } else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    //We can call the notify - because the other thread will be notified only if they are waiting (WAITING_STATE)
                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items");
                    lock.wait();
                } else {
                    System.out.println("Removing: " + value);
                    list.remove(list.size() - 1);
                    value--;
                    //We can call the notify - because the other thread is
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        new Thread(()-> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}