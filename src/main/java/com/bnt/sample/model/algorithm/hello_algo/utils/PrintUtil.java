package com.bnt.sample.model.algorithm.hello_algo.utils;

import java.util.ArrayList;
import java.util.List;

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
}
