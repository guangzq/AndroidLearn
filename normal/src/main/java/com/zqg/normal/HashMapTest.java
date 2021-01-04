package com.zqg.normal;

import android.os.MessageQueue;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
        hashMap.put(null, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(1, "p");
        hashMap.remove(3);
        Set<Integer> integers = hashMap.keySet();
        for (Integer integer: integers) {
            System.out.println("HashMap: key=" + integer+ ",value=" + hashMap.get(integer));
        }
        System.out.println("---------------------");
        //ArrayMap
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        arrayMap.put(null, "a");
        arrayMap.put(2, "b");
        arrayMap.put(3, "c");
        arrayMap.put(1, "p");
        Set<Integer> integer2 = arrayMap.keySet();
        for (Integer integer: integer2) {
            System.out.println("ArrayMap: key=" + integer+ ",value=" + arrayMap.get(integer));
        }
    }
}
