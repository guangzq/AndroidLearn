package com.zqg.multi_thread;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/4/29 11:28
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class Good {
    private int num;

    public void test() {
        int i = 0;
        synchronized (this) {
            num = i + 1;
        }
    }
}
