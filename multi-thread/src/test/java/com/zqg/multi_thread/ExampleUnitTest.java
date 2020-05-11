package com.zqg.multi_thread;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    final Object object = new Object();
    @Test
    public void addition_isCorrect() {
        final ExampleUnitTest test = new ExampleUnitTest();
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

//    private synchronized void getNum() {
//        int i = 0;
//        while (i < 10) {
//            i++;
//            System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
//        }
//    }

//    private static synchronized void getNum() {
//        int i = 0;
//        while (i < 10) {
//            i++;
//            System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
//        }
//    }

    private  void getNum() {
        synchronized (object) {
            int i = 0;
            while (i < 1000) {
                i++;
                System.out.println(Thread.currentThread().getName() + " print getNum: " + i);
            }
        }
    }
}