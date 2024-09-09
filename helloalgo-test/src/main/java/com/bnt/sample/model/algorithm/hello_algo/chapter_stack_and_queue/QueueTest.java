package com.bnt.sample.model.algorithm.hello_algo.chapter_stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 队列常用操作
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/3/4 13:38 bnt
 * @history
 */
public class QueueTest {
    public static void main(String[] args) {
        /*初始化队列*/
        Queue<Integer> queue = new LinkedList<>();

        /*元素入队*/
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);
        queue.offer(5);
        queue.offer(4);
        System.out.println("队列 queue = " + queue);

        /*访问队首元素*/
        Integer peek = queue.peek();
        System.out.println("队首元素 peek = " + peek);

        /*元素出队*/
        Integer poll = queue.poll();
        System.out.println("出队元素 poll = " + poll + "，出队后 queue = " + queue);

        /*获取队列的长度*/
        int size = queue.size();
        System.out.println("队列长度 size = " + size);

        /* 判断队列是否为空 */
        boolean isEmpty = queue.isEmpty();
        System.out.println("队列是否为空 = " + isEmpty);
    }
}
