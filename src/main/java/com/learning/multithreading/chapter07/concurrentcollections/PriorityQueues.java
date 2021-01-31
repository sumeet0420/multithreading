package com.learning.multithreading.chapter07.concurrentcollections;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueues {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(16,Comparator.reverseOrder());
        new Thread(()->{
            try {
                queue.put(5);
                queue.put(3);
                queue.put(2);
                queue.put(20);
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
