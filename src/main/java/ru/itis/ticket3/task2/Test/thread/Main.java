package ru.itis.ticket3.task2.Test.thread;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);

        thread.start();
    }
}
