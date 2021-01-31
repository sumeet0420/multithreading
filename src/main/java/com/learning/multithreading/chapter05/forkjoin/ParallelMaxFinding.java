package com.learning.multithreading.chapter05.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer> {

    private final int[] nums;
    private final  int lowIndex;
    private final int highIndex;
    private final int THRESHOLD;

    public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.highIndex = highIndex;
        this.lowIndex = lowIndex;
        THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();
    }

    public ParallelMaxFinding(int[] nums) {
        this(nums, 0, nums.length);
    }

    @Override
    protected Integer compute() {
        if ((highIndex - lowIndex) < THRESHOLD) {
            //System.out.println("Sequential");
            return SequentialMaxFinding.sequentialMaxFind(nums, lowIndex, highIndex);
        } else {
            //System.out.println("Parallel");
            final int middleIndex = (highIndex + lowIndex) / 2;
            ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
            ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex + 1, highIndex);
            invokeAll(task1, task2);
            return Math.max(task1.join(), task2.join());
        }
    }
}