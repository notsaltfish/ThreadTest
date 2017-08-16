package com.chen.threadsecurity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chenyong on 2017-08-10.
 * 线程安全主要问题出现在多线程操作共享数据的情况下
 * 出现线程安全的根本原因在于 数据操作并非是原子性的操作
 * 从主内存读取到本地内存到更新到主内存并不能一步完成
 * 读取到更新期间，线程随时可能因为CPU执行时间到达，而被操作系统
 * 从CPU取出，这样就有可能在这期间被其他的线程继续去读取并且修改主内存的
 * 那块数据，之后不管最后哪个线程先完成更新到主内存的操作，先更新到主内存的数据
 * 就会被后更新过来的数据直接覆盖掉，因为主内存并不会做对比前后数据是否有被修改。
 * 这样其中有一个线程所做的操作就是白费的，没有对应用程序起到作用
 * synchronized的作用就是保证 某段代码在没有被执行完之前 即使被操作系统从CPU中取出
 * 但是该线程获取了改对象的锁 还是保证其他的线程无法去读取修改目标数据
 */
public class SecurityTest {
    public static void main(String[] args) {
        String[] names = {"1","2","3","4","5","6"};
        SecurityTest test = new SecurityTest();
        SecurityTest.MyThreads myThreads = test.new MyThreads(names);

        Thread t1 = new Thread(myThreads,"t1");
        Thread t2 = new Thread(myThreads,"t2");
        Thread t3 = new Thread(myThreads,"t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.asList(names));

    }


     class MyThreads implements  Runnable{
        private String[] names = null;

         public MyThreads(String[] names) {
             this.names = names;
         }


         @Override
        public void run() {
             /**
             * 不加这个synchronized就会出现线程安全问题
              */
             synchronized (names) {
             for(int i=0;i<names.length;i++) {
                 sleepSomeTime(i);

                     addThreadName(i, Thread.currentThread().getName());
                 }
             }
        }

        public void addThreadName(int i,String name) {
                names[i] = names[i]+":"+name;

        }

        public void sleepSomeTime(int i){

            try {
                Thread.sleep(i*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
