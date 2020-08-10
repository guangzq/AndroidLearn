package com.zqg.threadlibrary;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/8/10 17:04
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
class AtomicCAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        for (int i = 0; i < 10; i++) {
            System.out.println(atomicInteger.getAndIncrement());
        }
    }
}
