package com.bnt.sample.model.algorithm.hello_algo.chapter_stack_and_queue;

import java.util.Arrays;

/**
 * 基于数组实现队列
 * --------
 * 可以使用一个变量 front 指向队首元素的索引，并维护一个变量 size 用于记录队列长度。
 * 定义 rear = front + size ，这个公式计算出的 rear 指向队尾元素之后的下一个位置。
 * 数组中包含元素的有效区间为 [front, rear - 1]
 */
class ArrayQueueTest {
    // 用于存储队列元素的数组
    private int[] nums;
    // 队首指针，指向队首元素
    private int front;
    // 队列长度
    private int queSize;

    public ArrayQueueTest(int capacity) {
        nums = new int[capacity];
        front = queSize = 0;
    }

    /* 获取队列的容量 */
    public int capacity() {
        return nums.length;
    }

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
        if (queSize == capacity()) {
            System.out.println("队列已满");
            return;
        }
        // 计算队尾指针，指向队尾索引 + 1
        // 通过取余操作实现 rear 越过数组尾部后回到头部
        int rear = (front + queSize) % capacity();
        nums[rear] = num;
        queSize++;
    }

    /* 出队 */
    public int pop() {
        int peek = peek();
        front = (front + 1) % capacity();
        queSize--;
        return peek;
    }

    /* 访问队首元素 */
    public int peek() {
        if (isEmpty()) throw new IndexOutOfBoundsException();
        return nums[front];
    }

    /* 返回数组 */
    public int[] toArray() {
        int[] res = new int[queSize];
        for (int i = 0, j = front; i < queSize; i++, j++) {
            int a = j % capacity();
            res[i] = nums[a];

        }
        return res;
    }

}

public class array_queue {
    public static void main(String[] args) {
        /* 初始化队列 */
        int capacity = 10;
        ArrayQueueTest queue = new ArrayQueueTest(capacity);

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

        /* 测试环形数组 */
        for (int i = 0; i < 10; i++) {
            queue.push(i);
            queue.pop();
            System.out.println("第 " + i + " 轮入队 + 出队后 queue = " + Arrays.toString(queue.toArray()));
        }
    }
}
