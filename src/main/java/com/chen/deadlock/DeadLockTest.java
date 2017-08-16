package com.chen.deadlock;

/**
 * Created by chenyong on 2017-08-15.
 * 死锁是指不同线程持有锁 并且还请求其他的锁
 * 而这个请求的锁 又被另外线程持有 这个另外的线程
 * 同时又请求前面线程的锁 并且，两个线程没有获得当前请求的
 * 锁的话，也不会释放当前以及拥有的锁
 * 这样产生循环依赖，就产生死锁
 *
 * 避免死锁 ，
 * 1.不要在同步的代码块里面再嵌入同步代码块
 * 2.可以调用线程的join方法，传入一个最大等待的时间
 * 如果还没有获取锁，也可以直接跳出该线程，而不会继续
 * 等待锁
 *
 * 死锁之后可以查看线程的dump文件 dump文件底部会有详细的关于死锁的信息
 * 可以通过jvisualvm或者 jstack 等方法查看dump
 */
public class DeadLockTest implements  Runnable {
        private  Object lock1;
        private  Object lock2;

    public DeadLockTest(Object object1, Object object2) {
        this.lock1 = object1;
        this.lock2 = object2;
    }

    public DeadLockTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockTest test = new DeadLockTest();
        DeadLockTest.Lock o1  = test.new Lock("1");
        DeadLockTest.Lock o2  = test.new Lock("2");
        DeadLockTest.Lock o3  = test.new Lock("3");

        DeadLockTest test1 = new DeadLockTest(o1,o2);
        DeadLockTest test2 = new DeadLockTest(o2,o3);
        DeadLockTest test3 = new DeadLockTest(o3,o1);
        Thread t1 = new Thread(test1,"t1");
        Thread t2 = new Thread(test2,"t2");
        Thread t3 = new Thread(test3,"t3");
        t1.start();
        t2.start();
        t3.start();

    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+" 正在请求 "+lock1);
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+" 获得了 "+lock1);
            sleepSomeTime();
            System.out.println(Thread.currentThread().getName()+"正在请求 "+lock2);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+" 获得了 "+lock2);
                sleepSomeTime();

            }
        }
        /**
         * 把里面的同步代码块放到下面来
         * 就可以避免导致死锁
         */


    }

    /**
     * 代表在实际开发中 可能需要执行其他的代码
     * 需要的时间 这里就用sleep来代替
     */
    private void sleepSomeTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Lock{
        private String  name;

        public Lock(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "锁"+name;
        }
    }
}
