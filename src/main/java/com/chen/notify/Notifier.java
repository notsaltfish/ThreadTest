package com.chen.notify;

/**
 * Created by chenyong on 2017-08-07.
 */
public class Notifier implements  Runnable {

    private  Message message;

    public Notifier(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" ready to  notified");
     //   synchronized (message){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message.setMessage("notifiy message");
         //   message.notify();
            message.notifyAll();
            System.out.println("notified ");
    //    }

    }
}
