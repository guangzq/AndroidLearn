package com.zqg.threadlibrary;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/6 15:55
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class SynThread {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Counter.mLock) {
                    for (int i=0;i<10000;i++) {
                        Counter.longValue++;
                        Counter.intValue++;
                    }
                }

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Counter.mLock) {
                    for (int i=0;i<10000;i++) {
                        Counter.longValue--;
                        Counter.intValue--;
                    }
                }

            }
        });
        System.out.println("start");
        thread1.start();
        thread2.start();
//        thread1.join();
//        thread2.join();
        System.out.println("longValue=" + Counter.longValue + ",intValue=" + Counter.intValue);
        System.out.println("end");
    }

    private synchronized void getValue() {

    }

    static class Counter {
        public final static Object mLock = new Object();
        public static long longValue = 0;
        public static int intValue = 0;
    }
}
