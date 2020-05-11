package com.zqg.threadlibrary;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/28 15:09
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ThreadLibTest {
    private Object object = new Object();
    private ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        final ThreadLibTest test = new ThreadLibTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.getNum();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.getNum();
            }
        });
        thread1.start();
        thread2.start();
    }

    /**
     * 1.缓存一致性问题
     * 2.指令重排
     */
    private void getNum() {
        reentrantLock.lock();
        try {
            int i = 0;
            while (i < 50) {
                i++;
                System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
            }
        }finally {
            reentrantLock.unlock();
        }
    }

//    private static synchronized void getNum() {
//        int i = 0;
//        while (i < 10) {
//            i++;
//            System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
//        }
//    }

//    private  void getNum() {
//        synchronized (object) {
//            int i = 0;
//            while (i < 10) {
//                i++;
//                System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
//            }
//        }
//    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
