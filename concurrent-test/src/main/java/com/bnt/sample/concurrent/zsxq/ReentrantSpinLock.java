package com.bnt.sample.concurrent.zsxq;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述： 实现一个可重入的自旋锁
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/4 16:09 bnt
 * @history
 */
public class ReentrantSpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 重入次数
    private int count = 0;

    public void lock() {
        Thread thread = Thread.currentThread();
        if (thread == owner.get()) {
            ++count;
            return;
        }
        // 自旋获取锁
        while (!owner.compareAndSet(null, thread)) {
            System.out.println("自旋了");
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        if (thread == owner.get()) {
            if (count > 0) {
                --count;
                return;
            } else {
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) {
        ReentrantSpinLock reentrantSpinLock = new ReentrantSpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                reentrantSpinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantSpinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了了自旋锁");
                }
                reentrantSpinLock.unlock();
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

    }
}
