package com.bnt.sample.model.algorithm.hello_algo.chapter_stack_and_queue;

import com.bnt.sample.model.algorithm.hello_algo.utils.ListNode;

import java.util.Arrays;

/**
 * 基于链表实现队列
 */
class LinkedListQueueTest {
    // 头节点 尾节点
    private ListNode front, rear;
    private int queSize = 0;

    /* 获取队列的长度 */
    public int size() {
        return queSize;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* 入队 */
    public void push(int num) {
        ListNode node = new ListNode(num);
        // 如果头节点为空，则头尾节点都指向该节点
        if (front == null) {
            front = node;
            rear = node;
            // 如果队列不为空，则将该节点添加到尾节点后
        } else {
            rear.next = node;
            rear = node;
        }
        queSize++;
    }

    /* 出队 */
    public int pop() {
        int peek = peek();
        // 删除头节点
        front = front.next;
        queSize--;
        return peek;
    }

    /* 访问队首元素 */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }

    /* 将链表转化为 Array 并返回 */
    public int[] toArray() {
        int[] array = new int[size()];
        ListNode node = front;
        for (int i = 0; i < size(); i++) {
            array[i] = node.val;
            node = node.next;
        }
        return array;
    }
}

public class linkedlist_queue {
    public static void main(String[] args) {
        /* 初始化队列 */
        LinkedListQueueTest queue = new LinkedListQueueTest();

        /* 元素入队 */
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(5);
        queue.push(4);
        System.out.println("队列 queue = " + Arrays.toString(queue.toArray()));

        /* 访问队首元素 */
        int peek = queue.peek();
        System.out.println("队首元素 peek = " + peek);

        /* 元素出队 */
        int pop = queue.pop();
        System.out.println("出队元素 pop = " + pop + "，出队后 queue = " + Arrays.toString(queue.toArray()));

        /* 获取队列的长度 */
        int size = queue.size();
        System.out.println("队列长度 size = " + size);

        /* 判断队列是否为空 */
        boolean isEmpty = queue.isEmpty();
        System.out.println("队列是否为空 = " + isEmpty);
    }
}
