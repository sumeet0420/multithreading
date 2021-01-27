package com.learning.multithreading.chapter01;

class DaemonWorker implements Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon Thread is running");
        }
    }
}

class NormalWorker implements Runnable{

    @Override
    public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Normal Thread is running...");
        }
    }

public class DaemonThreads {

    public static void main(String[] args) {
        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());
        t1.setDaemon(true);
        t1.start();
        t2.start();
    }
}
