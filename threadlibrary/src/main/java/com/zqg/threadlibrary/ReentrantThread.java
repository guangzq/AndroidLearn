package com.zqg.threadlibrary;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/10 11:42
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ReentrantThread {


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
        private  ReentrantLock lock = new ReentrantLock();
        private Condition mCondition;

        public TaskQueue() {
            mCondition = lock.newCondition();
        }

        public void addTask(int value) {
            lock.lock();
            try {
                queue.add(value);
                mCondition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public int getTask() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    mCondition.await();
                }
                return queue.remove();
            }finally {
                lock.unlock();
            }

        }
    }
}
