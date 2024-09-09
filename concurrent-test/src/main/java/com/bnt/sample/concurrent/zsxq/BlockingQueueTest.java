package com.bnt.sample.concurrent.zsxq;

import java.util.concurrent.*;

/**
 * 阻塞队列
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/18 14:52 bnt
 * @history
 */
public class BlockingQueueTest {
    /**
     * add 方法 抛出异常
     *
     * @param args
     */
    public static void main1(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
    }

    /**
     * remove 抛出异常
     *
     * @param args
     */
    public static void main2(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    /**
     * element 返回队列的头部节点，但是并不删除   抛出异常
     *
     * @param args
     */
    public static void main3(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.element();
    }

    /**
     * offer 插入元素 返回值表示插入是否成功
     *
     * @param args
     */
    public static void main4(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
    }

    /**
     * poll 移除并返回队列的头节点
     * @param args
     */
    public static void main5(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * peek 返回队列的头元素但不删除
     * @param args
     */
    public static void main6(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.peek());
    }

    /**
     * put tack
     * @param args
     */
    public static void main7(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.put(1);
        System.out.println(blockingQueue.take());
        blockingQueue.put(2);
        System.out.println(blockingQueue.take());
        blockingQueue.put(3);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.add(1);
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        synchronousQueue.size();
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        priorityBlockingQueue.size();
        DelayQueue<Delayed> delayQueue = new DelayQueue<>();
        delayQueue.put(new Delayed() {
            @Override
            public int compareTo(Delayed o) {
                return 0;
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return unit.toMinutes(10);
            }
        });

        ConcurrentLinkedQueue<Object> objects = new ConcurrentLinkedQueue<>();
    }
}
