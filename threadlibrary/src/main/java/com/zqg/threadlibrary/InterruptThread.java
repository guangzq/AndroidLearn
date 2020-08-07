package com.zqg.threadlibrary;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/7 10:44
 *     email : qiguang.zhu@foxmail.com
 *     desc  : 中断线程
 * </pre>
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        //1.通过interrupt方法
//        MyThread myThread = new MyThread();
//        myThread.start();
//        Thread.sleep(10);
//        myThread.interrupt();
        //2.利用volatile来设置线程间可共享的标志位
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
        Thread.sleep(10);
        myThread2.isRun = false;
    }

    private static class MyThread extends Thread {
        int n = 0;
        @Override
        public void run() {
            while (!isInterrupted()) {
                n++;
                System.out.println("n=" + n);
            }
        }
    }

    private static class MyThread2 extends Thread {
        public volatile boolean isRun = true;
        int n = 0;
        @Override
        public void run() {
            while (isRun) {
                n++;
                System.out.println("n=" + n);
            }
        }
    }
}
