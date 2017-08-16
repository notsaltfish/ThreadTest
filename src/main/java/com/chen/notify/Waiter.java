package com.chen.notify;

/**
 * Created by chenyong on 2017-08-07.
 */
public class Waiter implements  Runnable{

    private Message message;

    public Waiter(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is waiting for notify");
        synchronized (message){
            try {

                message.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" has been notified");
            System.out.println("notified msg: "+message.getMessage());
        }
    }
}
