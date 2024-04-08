package com.bnt.sample.model.algorithm.hello_algo.chapter_heap;

import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;

import java.util.*;

/**
 * 大顶堆实现
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/12 14:35 bnt
 * @history
 */
class MaxHeap {
    // 使用列表而非数组，无需考虑扩容问题
    private List<Integer> maxHeap;

    public MaxHeap(List<Integer> list) {
        maxHeap = new ArrayList<>(list);
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /* 获取左子节点的索引 */
    public int left(int i) {
        return 2 * i + 1;
    }

    /* 获取右子节点的索引 */
    public int right(int i) {
        return 2 * 1 + 2;
    }

    /* 获取父节点的索引 */
    public int parent(int i) {
        return (i - 1) / 2;
    }

    /* 交换元素 */
    public void swap(int i, int j) {
        Integer tmp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, tmp);
    }

    /*获取堆大小*/
    public int size() {
        return maxHeap.size();
    }

    /*判断堆是否为空*/
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /*访问堆顶*/
    public int peek() {
        return maxHeap.get(0);
    }

    /*元素入堆*/
    public void push(int val) {
        maxHeap.add(val);
        siftUp(maxHeap.size() - 1);
    }

    /* 从节点 i 开始，从底至顶堆化 */
    public void siftUp(int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = parent(i);
            // 当“越过根节点”或“节点无须修复”时，结束堆化
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p)) {
                break;
            }
            // 交换两节点
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    /* 元素出堆 */
    public int pop() {
        // 判空处理
        if (isEmpty()) throw new IndexOutOfBoundsException();
        // 交换根节点与最右叶节点（交换首元素与尾元素）
        swap(0, size() - 1);
        // 删除节点
        int val = maxHeap.remove(size() - 1);
        // 从顶至底堆化
        siftDown(0);
        return val;
    }

    /* 从节点 i 开始，从顶至底堆化 */
    private void siftDown(int i) {
        while (true) {
            int l = left(i), r = right(i), ma = i;
            // 判断节点 i, l, r 中值最大的节点，记为 ma
            if (l < maxHeap.size() && maxHeap.get(l) > maxHeap.get(ma)) {
                ma = l;
            }
            if (r < maxHeap.size() && maxHeap.get(r) > maxHeap.get(ma)) {
                ma = r;
            }
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
            if (ma == i) {
                break;
            }
            // 交换两节点
            swap(i, ma);
            // 循环向下堆化
            i = ma;
        }
    }

    /* 打印堆（二叉树） */
    public void print() {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        queue.addAll(maxHeap);
        PrintUtil.printHeap(queue);
    }
}

public class my_heap {
    public static void main(String[] args) {
        /* 初始化大顶堆 */
        MaxHeap maxHeap = new MaxHeap(Arrays.asList(9, 8, 6, 6, 7, 5, 2, 1, 4, 3, 6, 2));
        System.out.println("\n输入列表并建堆后");
        maxHeap.print();

        /* 获取堆顶元素 */
        int peek = maxHeap.peek();
        System.out.format("\n堆顶元素为 %d\n", peek);

        /* 元素入堆 */
        int val = 7;
        maxHeap.push(val);
        System.out.format("\n元素 %d 入堆后\n", val);
        maxHeap.print();

        /* 堆顶元素出堆 */
        peek = maxHeap.pop();
        System.out.format("\n堆顶元素 %d 出堆后\n", peek);
        maxHeap.print();

        /* 获取堆大小 */
        int size = maxHeap.size();
        System.out.format("\n堆元素数量为 %d\n", size);

        /* 判断堆是否为空 */
        boolean isEmpty = maxHeap.isEmpty();
        System.out.format("\n堆是否为空 %b\n", isEmpty);
    }
}
