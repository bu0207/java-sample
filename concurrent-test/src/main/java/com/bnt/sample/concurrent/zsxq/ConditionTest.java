package com.bnt.sample.concurrent.zsxq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition接口
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/2/1 11:47 bnt
 * @history
 */
public class ConditionTest {
    static class ConditionDemo {
        static ReentrantLock lock = new ReentrantLock();
        static Condition condition = lock.newCondition();

        static void method1() throws InterruptedException {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "条件不满足，开始等待");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "条件满足,开始执行后续流程");
            } finally {
                lock.unlock();
            }
        }

        static void method2() throws InterruptedException {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "需要5秒时间准备");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "准备工作完成，唤醒其他线程");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ConditionDemo conditionDemo = new ConditionDemo();
            new Thread(() -> {
                try {
                    conditionDemo.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            conditionDemo.method1();
        }
    }

    /**
     * Condition实现阻塞队列
     */
    static class MyBlockingQueueForCondition {
        static private Queue queue;
        static private int max = 16;
        static ReentrantLock reentrantLock = new ReentrantLock();
        static Condition notFull = reentrantLock.newCondition();
        static Condition notEmpty = reentrantLock.newCondition();

        public MyBlockingQueueForCondition(int max) {
            this.max = max;
            queue = new LinkedList();
        }

        static void put(Object o) throws InterruptedException {
            reentrantLock.lock();
            try {
                while (max == queue.size()) {
                    notFull.await();
                }
                queue.add(o);
                notEmpty.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        }

        static Object take() throws InterruptedException {
            reentrantLock.lock();
            try {
                while (queue.size() == 0) {
                    notEmpty.await();
                }
                Object item = queue.remove();
                notFull.signalAll();
                return item;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /**
     * wait/notify实现简易阻塞队列
     */
    static class MyBlockingQueueForWaitNotify {
        private int max;
        private LinkedList<Object> storage;

        public MyBlockingQueueForWaitNotify(int max) {
            this.max = max;
            storage = new LinkedList<>();
        }

        public synchronized void put(Object o) throws InterruptedException {
            while (max == storage.size()) {
                this.wait();
            }
            storage.add(o);
            this.notifyAll();
        }

        public synchronized Object take() throws InterruptedException {
            while (storage.size() == 0) {
                this.wait();
            }
            Object item = storage.remove();
            this.notifyAll();
            return item;
        }
    }
}
