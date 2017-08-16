package com.chen.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by chenyong on 2017-08-07.
 */
public class Producer implements Runnable{
    private BlockingQueue<Message> blockingQueue;

    public Producer(BlockingQueue<Message> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int i=1;
        while(true){
            try {
             //   Thread.sleep(1000);
                System.out.println("produce msg :"+i);
                Message message = new Message("msg:"+i);
                i++;
                blockingQueue.put(message);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
