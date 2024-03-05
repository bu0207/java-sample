package com.bnt.sample.model.algorithm.hello_algo.utils;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 输出
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/1 11:40 bnt
 * @history
 */
public class PrintUtil {

    /**
     * 输出链表
     */
    public static void printLinkedList(ListNode head) {
        List<String> list = new ArrayList<>();
        while (head != null) {
            list.add(String.valueOf(head.val));
            head = head.next;
        }
        System.out.println(String.join(",", list));
    }

    /**
     * 输出hashMap
     */
    public static <K, V> void printHashMap(Map<K, V> map) {
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            System.out.println(kvEntry.getKey() + " -> " + kvEntry.getValue());
        }
    }
}
