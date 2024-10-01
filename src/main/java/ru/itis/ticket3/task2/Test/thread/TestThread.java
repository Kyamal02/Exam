package ru.itis.ticket3.task2.Test.thread;

import static java.lang.Thread.sleep;

public class TestThread implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new TestThread());
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
    }
}
