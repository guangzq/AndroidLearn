package com.zqg.threadlibrary;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/6 15:55
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 running");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 running");
            }
        });
        System.out.println("start");
        thread1.start();
        thread2.start();
        System.out.println("end");
    }
}
