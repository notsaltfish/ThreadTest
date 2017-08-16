package com.chen.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by chenyong on 2017-08-07.
 * BlockingQueue阻塞队列用来做生产者和消费者的实例
 */
public class Service {

    public static void main(String[] args) {
        /**
         * 设置队列的大小为10
         */
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<Message>(10);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Thread t1 = new Thread(producer);
        Thread t2 =  new Thread(consumer);
        t1.start();
        t2.start();

    }

}
