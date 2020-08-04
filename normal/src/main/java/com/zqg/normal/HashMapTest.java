package com.zqg.normal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/7/1 16:30
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(1, "p");
        hashMap.remove(3);
        Set<Integer> integers = hashMap.keySet();
        for (Integer integer: integers) {
            System.out.println("key=" + integer+ ",value=" + hashMap.get(integer));
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.setDaemon(true);
    }
}
