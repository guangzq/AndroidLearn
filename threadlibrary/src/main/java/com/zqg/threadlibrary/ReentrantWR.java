package com.zqg.threadlibrary;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/7 17:22
 *     email : qiguang.zhu@foxmail.com
 *     desc  : 读写锁
 * </pre>
 */
public class ReentrantWR {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String num = "0";

    static class Reader implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 读取num is " + num);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }
    }

    static class Writer implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < 7; i += 2) {
                lock.writeLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在写入num is " + i);
                    num = num.concat("" + i);
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Reader(), "读线程1");
        Thread thread2 = new Thread(new Reader(), "读线程2");
        Thread thread3 = new Thread(new Writer(), "写线程");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
