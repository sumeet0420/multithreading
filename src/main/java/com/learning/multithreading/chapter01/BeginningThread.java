package com.learning.multithreading.chapter01;

class Runnable1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: "+Thread.currentThread().getName()+" at "+i);
        }
    }
}

class Runnable2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: "+Thread.currentThread().getName()+" at "+i);
        }
    }
}

class Runnable3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner3: "+Thread.currentThread().getName()+" at "+i);
        }
    }
}

public class BeginningThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable1());
        Thread t2 = new Thread(new Runnable2());
        Thread t3 = new Thread(new Runnable3());
        Thread t4 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Runner4: "+Thread.currentThread().getName()+" at "+i);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println("This is going to be printed very early");
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Fortunately, this is going to print after all the operations..");
    }

    public static void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
