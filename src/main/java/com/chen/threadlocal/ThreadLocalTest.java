package com.chen.threadlocal;

import java.text.SimpleDateFormat;

/**
 * Created by chenyong on 2017-08-15.
 * ThreadLocal 不会把修改好的共享变量覆盖到主内存
 * 所以这样每次其他线程去获取共享变量时 都是最开始的值，
 * 并且自己修改过的值也还存在于本地 而不会被其他的线程修改
 * 或者覆盖
 */
public class ThreadLocalTest implements  Runnable {
    /**
     * 传统的写法
     */
  /*  private  static  ThreadLocal<SimpleDateFormat> fomartter =
            new ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("yyyyMMdd");
                }
            };*/
    /**
     * java8lambda写法
     */
    private  static  ThreadLocal<SimpleDateFormat> fomartter =
            ThreadLocal.<SimpleDateFormat>withInitial(()->{
                return new SimpleDateFormat("yyyyMMdd");
            });

        public static void main(String[] args) {
            ThreadLocalTest threadLocalTest = new ThreadLocalTest();
            Thread t1 = new Thread(threadLocalTest,"t1");
            Thread t2 = new Thread(threadLocalTest,"t2");
            Thread t3 = new Thread(threadLocalTest,"t3");
            t1.start();
            t2.start();
            t3.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+fomartter.get().toPattern());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Thread.currentThread().getName().equals("t1")){
                fomartter.set(new SimpleDateFormat("yy"));
            }else if(Thread.currentThread().getName().equals("t2")){
                fomartter.set(new SimpleDateFormat("MM"));
            }else{
                fomartter.set(new SimpleDateFormat("dd"));
            }
            System.out.println(Thread.currentThread().getName()+":"+fomartter.get().toPattern());

        }
}
