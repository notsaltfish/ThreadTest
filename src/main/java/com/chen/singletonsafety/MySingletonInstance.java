package com.chen.singletonsafety;

/**
 * Created by chenyong on 2017-08-15.
 */
public class MySingletonInstance {
    private  static  MySingletonInstance instance = null;
    private static Object mutex = new Object();
    private MySingletonInstance(){

    }

    public  static MySingletonInstance  getInstance()  {
        /**
         * 为什么用两个if判断，外面这个if看起来好像是多余的
         * 但实际上避免了重复获取mutex对象的锁的操作，如果没有外面这个if判断
         * 那么每个线程进来即使instance已经new出来了 不为null
         * 但是还是要去获取这个mutex对象的锁 但是实际上这是没有必要的
         * 只有instance没有实例化出来的时候 才需要去获取mutex锁 对instance进行同步实例化操作
         *一旦new出来之后 就只要进行返回就可以 并不需要mutex的锁
         * 那为什么 synchronized里面还需要if判断呢？
         * 因为有可能同时有多个线程进入外面这个if代码块 如果其中一个线程已经完成了实例化的操作
         * 那么没有里面if判断的话  其他线程又已经进入外面的if判断的前提下
         * 还是会继续实例化一个instance出来 所以里面还需要再判断一次
         *
         * 另外 如果将synchronized放在方法级别上 会让执行效率变得非常的缓慢，因为这样
         * 每次线程都需要为获取锁而等待其他线程释放锁，
         * 当实例化这个对象之后 其实就不需要在对这个方法里面的对象进行同步操作了
         * 一切只有在最开始实例化对象时会产生线程安全问题
         *
         */
        if(instance==null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (mutex) {
                if (instance == null) {
                    instance = new MySingletonInstance();
                }
            }
        }
        return instance;
    }
}
