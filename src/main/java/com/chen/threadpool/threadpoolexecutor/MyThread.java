package com.chen.threadpool.threadpoolexecutor;

/**
 * Created by chenyong on 2017-08-16.
 */
public class MyThread implements  Runnable {

    private int index;

    public MyThread(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "MyThread "+index;
    }

    @Override
    public void run() {
        System.out.println("MyThread "+index+" is executing");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyThread "+index+" is terminated");
    }
}
