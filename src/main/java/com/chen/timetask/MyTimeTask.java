package com.chen.timetask;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenyong on 2017-08-15.
 * 建议任务执行间隔要比任务本身的执行时间长
 * 否则任务并不会减少 而会添加到执行队列
 * 这样一直到任务执行完成才算完成
 * 如果设置成守护线程 daemon 一旦main
 * 方法完成执行 该线程也会执行完毕 退出
 *
 */
public class MyTimeTask extends TimerTask {


    public static void main(String[] args) throws InterruptedException {
        MyTimeTask myTimeTask = new MyTimeTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(myTimeTask,0,5*10);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 取消之后不再执行
         */
        timer.cancel();
        Thread.sleep(20000);
        System.out.println("task is finished");
    }

    @Override
    public void run() {
        System.out.println("task is being executing at "+new Date());
        sleepSomeTime();
        System.out.println("task is finished  at      "+new Date());
    }

    private void sleepSomeTime() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
