package com.learning.multithreading.chapter04.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FixedCachePool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 10).forEach(i -> executorService.execute(new Task(i)));
        executorService.shutdown();
    }
}
