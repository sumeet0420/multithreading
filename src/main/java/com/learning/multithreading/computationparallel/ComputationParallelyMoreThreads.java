package com.learning.multithreading.computationparallel;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class ComputationParallelyMoreThreads {

    static int MAX = 1500;

    public static double compute(double number) {
        double result = 0;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                result += Math.sqrt(i * j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        try {
            var result = IntStream.range(0, MAX)
                    .parallel() //imp
                    .mapToDouble(ComputationParallelyMoreThreads::compute)
                    .sum();
            System.out.printf("result: %s%n",result);
        } finally {
            Duration duration = Duration.between(now, Instant.now());
            System.out.printf("Time taken is %dns%n", duration.getNano());
            System.out.printf("Time taken is %dms%n", duration.toMillis());
            System.out.printf("Time taken is %ds%n", duration.toSeconds());
        }
    }
}