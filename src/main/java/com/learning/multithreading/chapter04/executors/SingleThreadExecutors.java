package com.learning.multithreading.chapter04.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadExecutors {

    public static void main(String[] args) {
        //It is a single thread that will executors sequentially
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0,5).forEach(i->executorService.execute(new Task(i)));
        executorService.shutdown();
    }
}
