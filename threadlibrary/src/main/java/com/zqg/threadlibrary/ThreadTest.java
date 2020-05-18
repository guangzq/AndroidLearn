package com.zqg.threadlibrary;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/13 10:19
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ThreadTest {
    public static void main(String[] args) {
//        thread();
//        run();
        callable();
//        executor();
//        threadFactory();
    }

    private static void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("thread start");
            }
        };
        thread.start();
    }

    private static void run() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread by runnable");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                int a = 1+2;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return a + "";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(callable);
        try {
            String resultStr = result.get();
            System.out.println(resultStr);
            executorService.shutdown();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executor() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started!");
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(runnable);
    }

    private static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with threadFactory started!");
            }
        };
        Thread thread = threadFactory.newThread(runnable);
        thread.start();
    }
}
