package com.bnt.sample.model.algorithm.hello_algo.chapter_heap;

import com.bnt.sample.model.algorithm.hello_algo.utils.PrintUtil;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 大顶堆实现
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/12 14:35 bnt
 * @history
 */
public class MaxHeap {
    // 使用列表而非数组，无需考虑扩容问题
    private List<Integer> maxHeap;

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
            if (l < maxHeap.size() && maxHeap.get(l) > maxHeap.get(ma)) {
                ma = l;
            }
            if (r < maxHeap.size() && maxHeap.get(r) > maxHeap.get(ma)) {
                ma = r;
            }
            if (ma == i) {
                break;
            }
            swap(i, ma);
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
