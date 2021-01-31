package com.learning.multithreading.chapter05.forkjoin;

public class SequentialMaxFinding {

    public static int sequentialMaxFind(int[] nums) {
        return sequentialMaxFind(nums, 0, nums.length);
    }

    public static int sequentialMaxFind(int[] nums, int lowIndex, int highIndex) {
        int max = nums[lowIndex];
        for (int i = lowIndex; i < highIndex; i++) {
            if (nums[i] > max)
                max = nums[i];
        }
        return max;
    }
}
