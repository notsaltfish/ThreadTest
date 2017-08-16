package com.chen.singletonsafety;

import java.util.Random;

/**
 * Created by chenyong on 2017-08-15.
 * 多线程的情况下 单例模式可能会失效
 *
 * 因为在单例对象还没有被实例化之前，同时可能会有多个线程访问单例类的构造单例方法
 * 这里有有可能会造成返回的不是同一个单例对象
 *
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        Test.MyThread myThread = test.new MyThread();
        /*Thread t1 = new Thread(myThread,"t1");
        Thread t2 = new Thread(myThread,"t2");
        Thread t3 = new Thread(myThread,"t3");
        Thread t4 = new Thread(myThread,"t3");
        Thread t5 = new Thread(myThread,"t3");
        Thread t6 = new Thread(myThread,"t3");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();*/
        for(int i=0;i<1000;i++){
            Thread t = new Thread(myThread,"t"+i);
            t.start();
        }

    }

    class MyThread implements  Runnable {
        MySingletonInstance instance;
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = MySingletonInstance.getInstance();
            System.out.println(Thread.currentThread().getName()+":"+instance);
        }
    }
}
