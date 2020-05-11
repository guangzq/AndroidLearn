package com.zqg.threadlibrary;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/7 16:42
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ReentrantTest implements Runnable {
    private ReentrantLock reentrantLock = new ReentrantLock(false);

    @Override
    public void run() {
        int i = 0;
        while (i < 50) {
            reentrantLock.lock();
            try {
                i++;
                System.out.println(Thread.currentThread().getName() + " num is " + i);
            } finally {
                reentrantLock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        ReentrantTest reentrantTest = new ReentrantTest();
        Thread thread1 = new Thread(reentrantTest);
        Thread thread2 = new Thread(reentrantTest);
        Thread thread3 = new Thread(reentrantTest);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
