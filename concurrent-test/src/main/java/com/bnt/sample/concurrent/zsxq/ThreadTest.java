package com.bnt.sample.concurrent.zsxq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://learn.lianglianglee.com/%e4%b8%93%e6%a0%8f/Java%20%e5%b9%b6%e5%8f%91%e7%bc%96%e7%a8%8b%2078%20%e8%ae%b2-%e5%ae%8c/01%20%e4%b8%ba%e4%bd%95%e8%af%b4%e5%8f%aa%e6%9c%89%201%20%e7%a7%8d%e5%ae%9e%e7%8e%b0%e7%ba%bf%e7%a8%8b%e7%9a%84%e6%96%b9%e6%b3%95%ef%bc%9f.md
 * 01-05
 *
 * @author bnt
 * @version 1.0.0
 * @create 2023/12/26 17:09 bnt
 * @history
 */
public class ThreadTest {
    /**
     * 实现 Runnable 接口
     */
    public class RunnableThread implements Runnable {
        @Override
        public void run() {
            System.out.println("用实现Runnable接口实现线程");
        }
    }

    /**
     * 继承 Thread 类
     */
    public class ExtendsThread extends Thread {
        @Override
        public void run() {
            System.out.println("用Thread类实现线程");
        }
    }

    /**
     * 有返回值的 Callable 创建线程
     */
    class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return new Random().nextInt();
        }
    }

    public void test() {
        Thread thread = new Thread(new ExtendsThread());


        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //提交任务，并用 Future提交返回结果
        Future<Integer> submit = service.submit(new CallableTask());
        service.execute(new RunnableThread());

        /**
         * 匿名内部类创建线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }


    /**
     * 线程中断
     * sleep期间感受到中断
     */
    public static class StopThread implements Runnable {
        int count = 0;

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted() && count < 1000) {
                    System.out.println(count);
                    count++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main1(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new StopThread());
//        thread.start();
//        Thread.sleep(5);
//        thread.interrupt();
        // -------------------------------
//        VolatileCanStop volatileCanStop = new VolatileCanStop();
//        Thread thread1 = new Thread(volatileCanStop);
//        thread1.start();
//        Thread.sleep(3000);
//        volatileCanStop.canceled = true;
        // ---------------------------------------
//        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(8);
//        Producer producer = new Producer(storage);
//        Thread producerThread = new Thread(producer);
//        producerThread.start();
//        Thread.sleep(500);
//        Consumer consumer = new Consumer(storage);
//        while (consumer.needMoreNums()) {
//            System.out.println(consumer.storage.take() + "被消费了");
//            Thread.sleep(1000);
//        }
//        System.out.println("消费者不需要更多数据了。");

//        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况却停不下来
//        producer.canceled = true;
//        System.out.println(producer.canceled);
        // ----------------------------------------
    }

    /**
     * volatile 修饰标记位适用的场景
     */
    public static class VolatileCanStop implements Runnable {
        private boolean canceled = false;

        @Override
        public void run() {
            int num = 0;
            try {
                while (!canceled && num < 1000000) {
                    if (num % 10 == 0) {
                        System.out.println(num + "是10的倍数。");
                    }
                    num++;
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("结束运行");
            }
        }
    }


    /**
     * 生产者
     */
    static class Producer implements Runnable {
        public volatile boolean canceled = false;
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 1000000 && !canceled) {
                    if (num % 50 == 0) {
                        storage.put(num);
                        System.out.println(num + "是50的倍数,被放到仓库中了。");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.97) {
                return false;
            }
            return true;
        }
    }

    /**
     * 如何用 BlockingQueue 实现生产者消费者模式
     */
    public static void main2(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        Runnable producer = () -> {
            while (true) {
                try {
                    queue.put(new Object());
                    System.out.println("生产者生产了一个对象");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(producer).start();
        new Thread(producer).start();
        Runnable consumer = () -> {
            while (true) {
                try {
                    queue.take();
                    System.out.println("消费者消费了一个对象");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    /**
     * 用 Condition 实现生产者消费者模式
     */
    public static class MyBlockingQueueForCondition {
        private Queue queue;
        private int max = 16;
        private ReentrantLock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();

        public MyBlockingQueueForCondition(int size) {
            this.max = size;
            queue = new LinkedList();
        }

        public void put(Object o) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == max) {
                    notFull.await();
                }
                queue.add(o);
                System.out.println("生产者生产了一个对象");
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    notEmpty.await();
                }
                Object o = queue.remove();
                System.out.println("消费者消费了一个对象");
                notFull.signalAll();
                return o;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 用 wait/notify 实现生产者消费者模式
     */
    public static class MyBlockingQueueForWait {
        private int maxSize;
        private LinkedList<Object> storage;

        public MyBlockingQueueForWait(int maxSize) {
            this.maxSize = maxSize;
            storage = new LinkedList<>();
        }

        public synchronized void put(Object o) throws InterruptedException {
            while (storage.size() == maxSize) {
                wait();
            }
            storage.add(o);
            System.out.println("生产者生产了一个对象");
            notifyAll();
        }

        public synchronized void take() throws InterruptedException {
            while (storage.size() == 0) {
                wait();
            }
            System.out.println(storage.remove());
            notifyAll();
        }
    }

    /**
     * wait形式实现生产者消费者模式
     *
     * @param args
     */
    public static void main(String[] args) {
//        MyBlockingQueueForWait myBlockingQueue = new MyBlockingQueueForWait(10);
        MyBlockingQueueForCondition myBlockingQueue = new MyBlockingQueueForCondition(10);
        Runnable producer = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    myBlockingQueue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable consumer = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    myBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static void main4(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            get();
        };
        Thread thread = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable, "线程2");
        thread2.start();
        thread.start();
        Thread.sleep(100000);
    }

    public static synchronized void get() {
        System.out.println(Thread.currentThread().getName() + ">>>get");
//        Thread.interrupted();
    }
}
