package com.chen.futuretask;

import java.util.concurrent.*;

/**
 * Created by chenyong on 2017-08-16.
 * 线程池需要手动关闭
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        MyCallable callable = new MyCallable(1000);
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
                System.out.println("future task1 output = "+futureTask1.get());
            }

            System.out.println("Waiting for FutureTask2 to complete");
            String s = futureTask2.get(200l,TimeUnit.SECONDS);
            if(s!=null){
                System.out.println("future task2 output = "+s);
            }

        }
    }
}
