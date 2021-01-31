package com.learning.multithreading.chapter05.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MaxFinding {

    public static void main(String[] args) {

        var inputArray = ThreadLocalRandom.current().ints(5_00_000_000).toArray();
        //var inputArray = ThreadLocalRandom.current().ints(100_00_000).toArray();
        //var inputArray = IntStream.range(0,5_00_000_000).toArray();
        Instant now = Instant.now();
        try {
            //System.out.println(SequentialMaxFinding.sequentialMaxFind(inputArray));
            ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            System.out.println(forkJoinPool.invoke(new ParallelMaxFinding(inputArray)));
        } finally {
            Duration duration = Duration.between(now, Instant.now());
            System.out.printf("Time taken is %dns%n", duration.getNano());
            System.out.printf("Time taken is %dms%n", duration.toMillis());
            System.out.printf("Time taken is %ds%n", duration.toSeconds());
        }
    }
}
