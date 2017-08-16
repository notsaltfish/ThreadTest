package com.chen.threadpool.threadpoolexecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by chenyong on 2017-08-16.
 */
public class MyThreadMonitor implements  Runnable {

    private ThreadPoolExecutor executor;
    private boolean isShutdown=true;

    public MyThreadMonitor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public void shutdown(){
        this.isShutdown =false;
    }
    @Override
    public void run() {
        while (isShutdown){
            System.out.println(
                    String.format("[monitor] [%d/%d]  Active:%d, completed:%d, Task:%d, isShutdown:%s" +
                    " isTerminated:%s",
                    executor.getPoolSize(),
                    executor.getCorePoolSize(),
                    executor.getActiveCount(),
                    executor.getCompletedTaskCount(),
                    executor.getTaskCount(),
                    executor.isShutdown(),
                    executor.isTerminated()));
                if(executor.getActiveCount()==0){
                    executor.shutdown();
                    this.shutdown();
                }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
