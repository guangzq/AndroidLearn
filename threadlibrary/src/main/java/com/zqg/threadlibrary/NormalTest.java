package com.zqg.threadlibrary;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/14 10:26
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class NormalTest {

    public static void main(String[] args) {
//        waitTest();
        notifyTest();
    }

    private static void waitTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waitTest before");
                try {
                    synchronized (this) {
                        wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waitTest after");
            }
        });
        thread.start();
    }

    private static void notifyTest() {
        final NormalTest normalTest = new NormalTest();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    normalTest.waitNotify();
                }
            }).start();
        }
        synchronized (normalTest) {
            normalTest.notify();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------");
        synchronized (normalTest) {
            normalTest.notifyAll();
        }
    }

    private synchronized void waitNotify() {
        try {
            System.out.println(Thread.currentThread().getName() + " start");
            wait(0);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
