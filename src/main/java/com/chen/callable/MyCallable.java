package com.chen.callable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
/**
 * Created by chenyong on 2017-08-07.
 * 参考 http://www.journaldev.com/1090/java-callable-future-example
 * Callable相对于 Runnable的好处是  可以有返回值 可以抛出异常
 * Future 可以接受Callable执行的返回值
 * 而Runnable 则不行 这就是Callable的优势
 * Callable也是一个函数式接口
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return new Date()+":"+Thread.currentThread().getName();
    }

    public static void main(String[] args){
        /**
         * 线程池
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        Callable<String> myCallable = new MyCallable();
        for(int i =0;i<100;i++){
            Future future = executorService.submit(myCallable);
            futureList.add(future);

        }

        Iterator<Future<String>> iterator = futureList.iterator();
        /*
        *使用while循环 这样不必等在还在阻塞的future
        * 当一有future就会执行
        *而下面的for循环 很有可能会阻塞 等待正在阻塞的future执行完毕
        */
        while(!futureList.isEmpty()&&iterator.hasNext()){
            try {
                System.out.println(iterator.next().get());
            }catch(Exception e){
                e.printStackTrace();
            }
            iterator.remove();
        }
      /*  futureList.forEach(e->{
            try{
                System.out.println(e.get());
            }catch(Exception exc){
                exc.printStackTrace();
            }

        });*/

        /**
         * 执行完下面这段代码  所有线程被关闭释放
         */
        executorService.shutdown();
        System.out.println("shutdown");
    }
}
