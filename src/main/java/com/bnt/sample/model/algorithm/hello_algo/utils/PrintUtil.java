package com.bnt.sample.model.algorithm.hello_algo.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 输出
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/1 11:40 bnt
 * @history
 */
class Trunk {
    Trunk prev;
    String str;

    public Trunk(Trunk prev, String str) {
        this.prev = prev;
        this.str = str;
    }
}

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

    /**
     * 打印二叉树
     *
     * @param root
     */
    public static void printTree(TreeNode root) {
        printTree(root, null, false);
    }

    public static void printTree(TreeNode root, Trunk prev, boolean isRight) {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree(root.right, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        } else if (isRight) {
            trunk.str = "/———";
            prev_str = "   |";
        } else {
            trunk.str = "\\———";
            prev.str = prev_str;
        }

        showTrunks(trunk);
        System.out.println(" " + root.val);

        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";

        printTree(root.left, trunk, false);
    }

    /* 打印二叉树分支 */
    public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }

    /* Print a heap (PriorityQueue) */
    public static void printHeap(Queue<Integer> queue) {
        List<Integer> list = new ArrayList<>(queue);
        System.out.print("堆的数组表示：");
        System.out.println(list);
        System.out.println("堆的树状表示：");
        TreeNode root = TreeNode.listToTree(list);
        printTree(root);
    }
}
