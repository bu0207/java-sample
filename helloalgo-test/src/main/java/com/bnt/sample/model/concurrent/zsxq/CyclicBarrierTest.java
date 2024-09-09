package com.bnt.sample.model.concurrent.zsxq;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 示例
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/31 13:58 bnt
 * @history
 */
public class CyclicBarrierTest {

    /**
     * 模拟场景
     * 假设我们班级春游去公园里玩，并且会租借三人自行车，每个人都可以骑，
     * 但由于这辆自行车是三人的，所以要凑齐三个人才能骑一辆，而且从公园大门走到自行车驿站需要一段时间。
     */
    static class CyclicBarrierDemo {
        public static void main(String[] args) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
                @Override
                public void run() {
                    System.out.println("凑齐3人了！出发");
                }
            });
            for (int i = 0; i < 6; i++) {
                new Thread(new Task(i + 1, cyclicBarrier)).start();
            }
        }

        static class Task implements Runnable {
            private int id;

            private CyclicBarrier cyclicBarrier;

            public Task(int id, CyclicBarrier cyclicBarrier) {
                this.id = id;
                this.cyclicBarrier = cyclicBarrier;
            }

            @Override
            public void run() {
                System.out.println("同学" + id + "现在从大门出发，前往自行车驿站");
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("同学" + id + "到了自行车驿站，开始等待其他人到达");
                    cyclicBarrier.await();
                    System.out.println("同学" + id + "开始骑车");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
