package com.chen.notify;

/**
 * Created by chenyong on 2017-08-07.
 */
public class Test {

    public static void main(String[] args) {
        Message message = new Message();
        Waiter waiter1 = new Waiter(message);
        Waiter waiter2 = new Waiter(message);
        Notifier notifier = new Notifier(message);

        new Thread(waiter1,"waiter1").start();
        new Thread(waiter2,"waiter2").start();
        new Thread(notifier,"notifier1").start();
        System.out.println("ssss");
    }
}
