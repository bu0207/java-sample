package com.bnt.sample.model.concurrent.zsxq;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch使用
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/31 10:21 bnt
 * @history
 */
public class CountDownLatchTest {
    /**
     * 用法一：一个线程等待其他多个线程都执行完毕，再继续自己的工作
     */
    static class RunDemo1 {
        public static void main(String[] args) {
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            CountDownLatch countDownLatch = new CountDownLatch(5);
            for (int i = 0; i < 5; i++) {
                int no = i + 1;
                threadPool.execute(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(no + "号运动员完成了比赛。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            System.out.println("等待5个运动员都跑完.....");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("所有人都跑完了，比赛结束。");
            threadPool.shutdown();
        }
    }

    /**
     * 用法二：多个线程等待某一个线程的信号，同时开始执行
     */
    static class RunDemo2 {
        public static void main(String[] args) throws InterruptedException {
            System.out.println("运动员有5秒的准备时间.....");
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            for (int i = 0; i < 5; i++) {
                int no = i + 1;
                threadPool.execute(() -> {
                    System.out.println(no + "号运动员准备完毕，等待裁判员的发令枪");
                    try {
                        countDownLatch.await();
                        System.out.println(no + "号运动员开始跑步了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            Thread.sleep(5000);
            System.out.println("5秒准备时间已过，发令枪响，比赛开始！");
            countDownLatch.countDown();
        }

    }
}
