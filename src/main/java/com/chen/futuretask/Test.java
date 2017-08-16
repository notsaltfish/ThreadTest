package com.chen.futuretask;

import java.util.concurrent.*;

/**
 * Created by chenyong on 2017-08-16.
 * 线程池需要手动关闭
 * futureTask 的get方法会一直等待任务执行完毕才输出
 * 也就是说他是一个阻塞的方法
 *
 *
 * FutureTask 和Future是有区别的
 * FutureTask 需要一个Callable来传入构造函数
 * 而Future则不需要
 * 因为在线程池执行任务的时候 调用的方法也不一样
 * FutureTask被使用的时候 调用的是线程池的execute方法
 * 而Future被使用时，调用的是线程池的submit放方法
 * 返回值是future
 *
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        MyCallable callable = new MyCallable(10000);
        MyCallable callable2 = new MyCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
          executorService.execute(futureTask1);
          executorService.execute(futureTask2);

        while (true) {
            if(futureTask1.isDone()&&futureTask2.isDone()){
                System.out.println( "task is done ");
                executorService.shutdown();
                return;
            }

            if(!futureTask1.isDone()){
                /**
                 * get方法会一直等待任务执行完毕 才执行下面的 代码
                 * 阻塞的
                 */
                System.out.println("等在执行输出");
                System.out.println("future task1 output = "+futureTask1.get());
                System.out.println("执行完毕");
            }

            System.out.println("Waiting for FutureTask2 to complete");
            String s = futureTask2.get(200l,TimeUnit.SECONDS);
            if(s!=null){
                System.out.println("future task2 output = "+s);
            }

        }
    }
}
