package com.bnt.sample.model.concurrent.zsxq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.*;

/**
 * atomic 原子类
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/22 11:16 bnt
 * @history
 */
public class AtomicTest {
    public static void main1(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndAdd(-1);
        atomicInteger.compareAndSet(1, 2);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.getAndSet(true);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[10]);
        AtomicReference<Integer> integerAtomicReference = new AtomicReference<>();
        integerAtomicReference.getAndUpdate(v -> v + 1);
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<Integer>(integerAtomicReference.get(), 1);
        AtomicMarkableReference<Object> objectAtomicMarkableReference = new AtomicMarkableReference<>(integerAtomicReference.get(), true);
        objectAtomicMarkableReference.isMarked();

    }

    static class AtomicIntegerFieldUpdaterDemo implements Runnable {
        static Score math;
        static Score computer;

        public static AtomicIntegerFieldUpdater<Score> updater = AtomicIntegerFieldUpdater.newUpdater(Score.class, "score");

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                math.score++;
                updater.getAndIncrement(computer);
            }
        }

        public static void main2(String[] args) throws InterruptedException {
            math = new Score();
            computer = new Score();
            AtomicIntegerFieldUpdaterDemo atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo();
            Thread thread = new Thread(atomicIntegerFieldUpdaterDemo);
            Thread thread2 = new Thread(atomicIntegerFieldUpdaterDemo);

            thread.start();
            thread2.start();

            thread.join();
            thread2.join();

            System.out.println("普通变量的结果："+ math.score);
            System.out.println("升级后的结果："+ computer.score);

            LongAdder longAdder = new LongAdder();
            longAdder.add(1);
        }
    }

    public static class Score {
        volatile int score;
    }

    /**
     * AtomicLong高并发下存在的问题
     */
    static class Task implements Runnable {
        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }

    static class Task2 implements Runnable {
        private final LongAdder counter;

        public Task2(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        AtomicLong counter = new AtomicLong(0);
//        ExecutorService service = Executors.newFixedThreadPool(16);
//        for (int i = 0; i < 100; i++) {
//            service.execute(new Task(counter));
//        }
//        Thread.sleep(2000);
//        System.out.println(counter.get());

        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            service.execute(new Task2(counter));
        }
        Thread.sleep(2000);
        System.out.println(counter.sum());
    }
}
