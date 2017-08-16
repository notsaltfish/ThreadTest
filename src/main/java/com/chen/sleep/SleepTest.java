package com.chen.sleep;

/**
 * Created by chenyong on 2017-08-07.
 * sleep 不会释放已经占用的锁
 * 而wait  notify notifyAll 会
 */
public class SleepTest {
    public static void main(String[] args) throws  Exception {
        long current = System.nanoTime();
        System.out.println(current);
        Thread.sleep(1000);
        long end  = System.nanoTime();
        System.out.println(end);
        System.out.println(end-current);
        System.out.println((end-current)/1000000000d);

    }
}
