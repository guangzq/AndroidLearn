package com.zqg.threadlibrary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/10 11:42
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class NotifyThread {
    public static void main(String[] args) {
        final TaskQueue taskQueue = new TaskQueue();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("getTask=" + taskQueue.getTask());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    taskQueue.addTask(i);
                }
            }
        });
        thread.start();
    }

    private static class TaskQueue {
        private Queue<Integer> queue = new LinkedList<>();

        public synchronized void addTask(int value) {
            queue.add(value);
            notifyAll();
        }

        public synchronized int getTask() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            return queue.remove();
        }
    }
}
