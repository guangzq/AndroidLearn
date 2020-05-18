package com.zqg.threadlibrary;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/14 15:25
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class SleepWaitTest {

    public static void main(String[] args) {
        sleepTest();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------next is wait-------------");
        waitTest();
    }

    private static void sleepTest() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleepA();
                }
            }).start();
        }
    }

    private synchronized static void sleepA() {
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    private static void waitTest() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " start");
                    try {
                        synchronized (this) {
                            wait(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            }).start();
        }
    }
}
