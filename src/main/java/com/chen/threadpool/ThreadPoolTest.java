package com.chen.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenyong on 2017-08-15.
 * 线程池 初始化的是线程池的大小，即有几个线程来执行
 * 任务
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolTest threadPoolTest =  new ThreadPoolTest();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=1;i<=10;i++){
                ThreadPoolTest.WorkerThread workerThread = threadPoolTest.new WorkerThread(i);
                service.execute(workerThread);
        }
        service.shutdown();
        while (!service.isTerminated()){}
        System.out.println("Finished all task");
    }

    class WorkerThread implements  Runnable{

        private  int index;

        public WorkerThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" worker "+index+" is working now ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" worker "+index+" is finished now ");
        }
    }
}
