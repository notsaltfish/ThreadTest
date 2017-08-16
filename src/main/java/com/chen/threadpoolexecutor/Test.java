package com.chen.threadpoolexecutor;

import java.util.concurrent.*;

/**
 * Created by chenyong on 2017-08-16.
 * 这里是测试线程池 以及写一个线程池的监控器
 * ThreadPoolExecutor 线程池的创建比较麻烦
 * 前面几个 可以根据参数前面的参数名字大概
 * 知道是干什么的  后面的blockqueue 主要是用于
 * 当线程池里面的任务达到最大限制时，那么在外面可以有多少任务
 * 进行等待线程池执行完毕 再进入线程池
 * 也就是说线程池最终能够执行的任务量是最大线程池容量
 * 加上阻塞的任务数，然后超出等待阻塞的任务
 * 将会被rejectionHandler 拒绝
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        RejectionHandlerImpl rejectionHandler = new RejectionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,4,10,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1),threadFactory,rejectionHandler);
        MyThreadMonitor myThreadMonitor = new MyThreadMonitor(poolExecutor);
        Thread thread = new Thread(myThreadMonitor);
        thread.start();
        for(int i=1;i<=10;i++){
            poolExecutor.execute(new MyThread(i));
        }


        /**
         * 可以在监控器里面当线程池的activeCount变为0的时候将其关闭shutdown
         * 也可以在这里手动关闭
         */
        //Thread.sleep(10000);
      //  myThreadMonitor.shutdown();
        //poolExecutor.shutdown();
    }
}
