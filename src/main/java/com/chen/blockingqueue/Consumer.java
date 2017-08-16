package com.chen.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by chenyong on 2017-08-07.
 */
public class Consumer implements  Runnable {

    private BlockingQueue<Message> messages;

    public Consumer(BlockingQueue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        /**
         * 不手动停止 便无法停止
         */
        while(true){
            try {
                Thread.sleep(1000);
                Message message = messages.take();
                System.out.println("consumer "+message.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
