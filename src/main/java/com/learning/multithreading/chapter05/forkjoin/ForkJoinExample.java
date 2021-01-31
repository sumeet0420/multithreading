package com.learning.multithreading.chapter05.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * fork() --> asynchronously execute the given task in the pool
 *
 * join() --> returns the result
 */
public class ForkJoinExample {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveAction simpleRecursiveActionSequentially = new SimpleRecursiveAction(30);
        forkJoinPool.invoke(simpleRecursiveActionSequentially);
        SimpleRecursiveAction simpleRecursiveActionParallely = new SimpleRecursiveAction(130);
        forkJoinPool.invoke(simpleRecursiveActionParallely);
    }
}
