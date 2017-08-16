package com.chen.join;

/**
 * Created by chenyong on 2017-08-07.
 * 线程Join 方法 代表 执行完  当前调用join的线程 之后
 * 才会往下执行，
 * 有参数的join（long millsecond）方法，代表等待带该线程执行这一段时间之后再往下执行
 *
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new JoinTestThread(),"t1");
        Thread t2 = new Thread(new JoinTestThread(),"t2");
        Thread t3 = new Thread(new JoinTestThread(),"t3");

        t1.start();
       t1.join(3000);//小于或者等于t1的睡眠时间都不够等待执行完毕

        t2.start();
        t1.join();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("All Threads is executed,exiting main thread");
    }


   static   class JoinTestThread implements  Runnable{
        @Override
        public void run() {
            System.out.println("Thread started::"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ended::"+Thread.currentThread().getName());

        }
    }
}
