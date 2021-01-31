package com.learning.multithreading.chapter05.forkjoin;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    int workLoad;

    public SimpleRecursiveAction(int workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if (workLoad>100) {
            System.out.println("Parallel Work Required..");
            SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(workLoad/2);
            SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(workLoad/2);
            //Fork
            simpleRecursiveAction1.fork();
            simpleRecursiveAction2.fork();
        } else {
            System.out.printf("Parallel Algorith is not required for %s tasks%n",workLoad);
        }
    }
}
