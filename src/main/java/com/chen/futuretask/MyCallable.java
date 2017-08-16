package com.chen.futuretask;

import java.util.concurrent.Callable;

/**
 * Created by chenyong on 2017-08-16.
 */
public class MyCallable implements Callable<String> {

    private long waitTime;

    public MyCallable(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}
