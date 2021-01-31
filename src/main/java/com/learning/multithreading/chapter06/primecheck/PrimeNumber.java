package com.learning.multithreading.chapter06.primecheck;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class PrimeNumber {

    public static boolean isPrime(int number) {
        if(number<1) return false;
        if(number==2) return true;
        if(number%2==0) return false;

        final int maxDivison = (int) Math.sqrt(number);
        for(int i=3;i<maxDivison;i=i+2) {
            if(number%i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        try {
            System.out.println(getPrimeNumberCount(false));
        } finally {
            Duration duration = Duration.between(now, Instant.now());
            System.out.printf("Time taken is %dns%n", duration.getNano());
            System.out.printf("Time taken is %dms%n", duration.toMillis());
            System.out.printf("Time taken is %ds%n", duration.toSeconds());
        }
    }

    private static long getPrimeNumberCount(boolean isParallel) {
        return isParallel ? IntStream.range(1, 10_000_000).parallel().filter(PrimeNumber::isPrime).count()
                 : IntStream.range(1, 10_000_000).filter(PrimeNumber::isPrime).count();
    }
}
