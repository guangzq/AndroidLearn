package com.zqg.threadlibrary;

import java.util.LinkedList;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/6 9:56
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class AzPrint {
    private final char[] aZ = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'};

    /**
     * 用三个线程，顺序打印字母A～Z，输出结果是1A，2B，3C，1D，2E...。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        new Thread(new TRunnable(0, list), "线程1").start();
        new Thread(new TRunnable(1, list), "线程2").start();
        new Thread(new TRunnable(2, list), "线程3").start();

    }


    private static class TRunnable implements Runnable {
        private final static Object object = new Object();
        private final int threadNo;
        private final LinkedList<Integer> list;
        private static int count;

        public TRunnable(int threadNo, LinkedList<Integer> list) {
            this.threadNo = threadNo;
            this.list = list;
        }

        @Override
        public void run() {
            synchronized (object) {
                while (!list.isEmpty()) {
                    if (count % 3 == threadNo) {
                        System.out.println("thread-" + threadNo + "==>" + list.poll());
                        object.notifyAll();
                    }else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                   count++;
                }
            }
        }
    }
}
