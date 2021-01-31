package com.learning.multithreading.chapter07.concurrentcollections;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentAdd {

    public static void main(String[] args) throws InterruptedException{
        //List<Integer> list = new ArrayList<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10900; i++) {
                list.add(i);
            }
            list.add(909111);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10900; i++) {
                list.add(i);
            }
            list.add(909112);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
        //System.out.println(list.get(18000));
        System.out.println(list.contains(909111));
        System.out.println(list.contains(909112));
    }

}
