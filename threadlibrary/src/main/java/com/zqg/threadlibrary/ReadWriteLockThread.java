package com.zqg.threadlibrary;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/10 15:42
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class ReadWriteLockThread {
    private int[] ints = new int[10];
    private final ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock mReadLock;
    private final ReentrantReadWriteLock.WriteLock mWriteLock;

    public ReadWriteLockThread() {
        mReadLock = mLock.readLock();
        mWriteLock = mLock.writeLock();
    }

    public void write(int index) {
        mWriteLock.lock();
        try {
            ints[index] += 1;
        } finally {
            mWriteLock.unlock();
        }
    }

    public int[] read() {
        mReadLock.lock();
        try {
            return Arrays.copyOf(ints, ints.length);
        } finally {
            mReadLock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockThread thread = new ReadWriteLockThread();
        for (int i=0;i<6;i++) {
            thread.write(i);
        }
        Thread t0 = new Thread(new Run("thread1-", thread));
        Thread t1 = new Thread(new Run("thread2-", thread));
        Thread t2 = new Thread(new Run("thread3-", thread));
        t0.start();
        t1.start();
        t2.start();
    }

    private static class Run extends Thread {
        private String threadNo;
        ReadWriteLockThread thread;
        public Run(String threadNo, ReadWriteLockThread thread) {
            this.threadNo = threadNo;
            this.thread = thread;
        }

        @Override
        public void run() {
            super.run();
            int[] read = thread.read();
            for (int value : read) {
                System.out.println(threadNo + "==>value=" + value);
            }
        }
    }
}
